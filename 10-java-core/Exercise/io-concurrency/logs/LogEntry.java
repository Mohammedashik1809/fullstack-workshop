package io_concurrency;

import java.time.LocalDateTime;

public record LogEntry(
        LocalDateTime timestamp,
        String level,
        String message
) {}
