package file_processing;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;

public class TextFileTransformer {

    private final List<LineTransformer> transformers = new ArrayList<>();

    public TextFileTransformer addTransformer(LineTransformer t) {
        transformers.add(t);
        return this;
    }

    public TextFileTransformer clearTransformers() {
        transformers.clear();
        return this;
    }

    public void transform(Path source, Path destination) {
        try {
            List<String> lines = Files.readAllLines(source, StandardCharsets.UTF_8);
            List<String> result = applyTransformers(lines);
            Files.write(destination, result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void transformInPlace(Path file) {
        transform(file, file);
    }

    public void transformWithFilter(Path source, Path destination,
                                    Predicate<String> filter) {
        try {
            List<String> lines = Files.readAllLines(source, StandardCharsets.UTF_8);
            List<String> filtered = lines.stream()
                    .filter(filter)
                    .toList();
            Files.write(destination, applyTransformers(filtered), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> applyTransformers(List<String> lines) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            String out = line;
            for (LineTransformer t : transformers) {
                out = t.transform(out);
                if (out == null) break;
            }
            if (out != null) result.add(out);
        }
        return result;
    }
}
