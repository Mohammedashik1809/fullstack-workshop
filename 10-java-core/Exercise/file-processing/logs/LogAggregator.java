package file_processing;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogAggregator {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<LogEntry> parseLogFile(Path logFile) {
        try {
            return Files.readAllLines(logFile, StandardCharsets.UTF_8).stream()
                    .map(line -> {
                        String ts = line.substring(0, 19);
                        String level = line.substring(21, line.indexOf(']'));
                        String msg = line.substring(line.indexOf(']') + 2);
                        return new LogEntry(
                                LocalDateTime.parse(ts, FMT),
                                level,
                                logFile.getFileName().toString(),
                                msg
                        );
                    }).toList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public List<LogEntry> aggregateLogs(List<Path> logFiles) {
        return logFiles.stream()
                .flatMap(p -> parseLogFile(p).stream())
                .sorted()
                .toList();
    }

    public List<LogEntry> aggregateFromDirectory(Path dir, String pattern) {
        try {
            return Files.list(dir)
                    .filter(p -> p.getFileName().toString().matches(
                            pattern.replace("*", ".*")))
                    .flatMap(p -> parseLogFile(p).stream())
                    .sorted()
                    .toList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public List<LogEntry> filterByTimeRange(List<LogEntry> logs,
                                            LocalDateTime start,
                                            LocalDateTime end) {
        return logs.stream()
                .filter(l -> !l.timestamp().isBefore(start)
                        && !l.timestamp().isAfter(end))
                .toList();
    }

    public List<LogEntry> filterByLevel(List<LogEntry> logs, String... levels) {
        Set<String> set = Set.of(levels);
        return logs.stream()
                .filter(l -> set.contains(l.level()))
                .toList();
    }

    public List<LogEntry> searchByPattern(List<LogEntry> logs, String regex) {
        Pattern p = Pattern.compile(regex);
        return logs.stream()
                .filter(l -> p.matcher(l.message()).matches())
                .toList();
    }

    public LogSummary generateSummary(List<LogEntry> logs) {
        Map<String, Integer> byLevel = new HashMap<>();
        Map<String, Integer> bySource = new HashMap<>();

        logs.forEach(l -> {
            byLevel.merge(l.level(), 1, Integer::sum);
            bySource.merge(l.source(), 1, Integer::sum);
        });

        return new LogSummary(
                logs.size(),
                byLevel,
                bySource,
                logs.get(0).timestamp(),
                logs.get(logs.size() - 1).timestamp(),
                filterByLevel(logs, "ERROR")
        );
    }

    public void writeMergedLogs(Path destination, List<LogEntry> logs) {
        try (BufferedWriter w =
                     Files.newBufferedWriter(destination, StandardCharsets.UTF_8)) {
            for (LogEntry l : logs) {
                w.write(String.format(
                        "%s [%s] %s",
                        l.timestamp(), l.level(), l.message()
                ));
                w.newLine();
            }
        } catch (Exception ignored) {}
    }
}
