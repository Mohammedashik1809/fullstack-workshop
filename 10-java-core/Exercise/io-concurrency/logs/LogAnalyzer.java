package io_concurrency;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<LogEntry> readLogs(Path file) throws IOException {
        return Files.readAllLines(file).stream()
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private LogEntry parseLine(String line) {
        String[] parts = line.split(" ", 4);
        LocalDateTime ts = LocalDateTime.parse(parts[0] + " " + parts[1], FORMAT);
        return new LogEntry(ts, parts[2], parts[3]);
    }

    public Map<String, Long> countByLevel(List<LogEntry> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(
                        LogEntry::level, Collectors.counting()));
    }

    public List<LogEntry> getErrors(List<LogEntry> logs) {
        return logs.stream()
                .filter(l -> l.level().equals("ERROR"))
                .toList();
    }

    public void writeSummary(Path output, List<LogEntry> logs) throws IOException {
        List<String> lines = new ArrayList<>();
        countByLevel(logs).forEach((k, v) ->
                lines.add(k + ": " + v));
        Files.write(output, lines);
    }
}
