package file_processing;

import java.time.LocalDateTime;

public record LogEntry(
        LocalDateTime timestamp,
        String level,
        String source,
        String message
) implements Comparable<LogEntry> {

    @Override
    public int compareTo(LogEntry o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
