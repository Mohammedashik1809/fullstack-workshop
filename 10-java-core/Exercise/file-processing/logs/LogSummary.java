package file_processing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record LogSummary(
        int totalEntries,
        Map<String, Integer> countByLevel,
        Map<String, Integer> countBySource,
        LocalDateTime earliestEntry,
        LocalDateTime latestEntry,
        List<LogEntry> errors
) {}
