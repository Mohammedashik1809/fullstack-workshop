package file_processing;

import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

public class DirectoryScanner {

    public List<Path> findByPattern(Path directory, String globPattern) {
        PathMatcher matcher =
                FileSystems.getDefault().getPathMatcher("glob:" + globPattern);

        try {
            return Files.walk(directory)
                    .filter(Files::isRegularFile)
                    .filter(p -> matcher.matches(directory.relativize(p)))
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    public List<Path> findRecentFiles(Path directory, int days) {
        long cutoff = System.currentTimeMillis() - days * 86400000L;

        try {
            return Files.walk(directory)
                    .filter(Files::isRegularFile)
                    .filter(p -> {
                        try {
                            return Files.getLastModifiedTime(p).toMillis() >= cutoff;
                        } catch (IOException e) {
                            return false;
                        }
                    }).toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    public List<Path> findLargeFiles(Path directory, long minSize) {
        try {
            return Files.walk(directory)
                    .filter(Files::isRegularFile)
                    .filter(p -> {
                        try {
                            return Files.size(p) >= minSize;
                        } catch (IOException e) {
                            return false;
                        }
                    }).toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    public Map<String, List<Path>> findDuplicates(Path directory) {
        Map<String, List<Path>> map = new HashMap<>();

        try {
            Files.walk(directory)
                    .filter(Files::isRegularFile)
                    .forEach(p -> {
                        try {
                            String hash = hashFile(p);
                            map.computeIfAbsent(hash, k -> new ArrayList<>()).add(p);
                        } catch (Exception ignored) {}
                    });
        } catch (IOException ignored) {}

        return map.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private String hashFile(Path file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = Files.readAllBytes(file);
        byte[] hash = md.digest(bytes);
        return Base64.getEncoder().encodeToString(hash);
    }

    public DirectoryStats getStats(Path directory) {
        int files = 0, dirs = 0;
        long size = 0;
        Map<String, Integer> extMap = new HashMap<>();

        try {
            for (Path p : Files.walk(directory).toList()) {
                if (Files.isDirectory(p)) {
                    dirs++;
                } else if (Files.isRegularFile(p)) {
                    files++;
                    size += Files.size(p);
                    String ext = getExtension(p);
                    extMap.merge(ext, 1, Integer::sum);
                }
            }
        } catch (IOException ignored) {}

        return new DirectoryStats(files, dirs, size, extMap);
    }

    private String getExtension(Path p) {
        String name = p.getFileName().toString();
        int idx = name.lastIndexOf('.');
        return idx > 0 ? name.substring(idx + 1) : "none";
    }

    public int copyMatchingFiles(Path source, Path destination, String globPattern) {
        try {
            Files.createDirectories(destination);
        } catch (IOException ignored) {}

        List<Path> matches = findByPattern(source, globPattern);
        matches.forEach(p -> {
            try {
                Files.copy(p, destination.resolve(p.getFileName()),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ignored) {}
        });
        return matches.size();
    }
}
