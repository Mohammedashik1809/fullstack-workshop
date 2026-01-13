package file_processing;

import java.util.concurrent.atomic.AtomicInteger;

public class Transformers {

    public static LineTransformer toUpperCase() {
        return String::toUpperCase;
    }

    public static LineTransformer toLowerCase() {
        return String::toLowerCase;
    }

    public static LineTransformer trim() {
        return String::trim;
    }

    public static LineTransformer addLineNumbers() {
        AtomicInteger counter = new AtomicInteger(1);
        return line -> String.format("%03d: %s", counter.getAndIncrement(), line);
    }

    public static LineTransformer replace(String pattern, String replacement) {
        return line -> line.replaceAll(pattern, replacement);
    }

    public static LineTransformer removeEmptyLines() {
        return line -> line.isBlank() ? null : line;
    }

    public static LineTransformer indent(int spaces) {
        String pad = " ".repeat(spaces);
        return line -> pad + line;
    }

    public static LineTransformer wrapAt(int maxWidth) {
        return line -> line.length() <= maxWidth
                ? line
                : line.substring(0, maxWidth);
    }
}
