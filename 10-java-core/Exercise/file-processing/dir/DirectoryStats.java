package file_processing;

import java.util.Map;

public record DirectoryStats(
        int totalFiles,
        int totalDirectories,
        long totalSize,
        Map<String, Integer> filesByExtension
) {}
