package file_processing;

@FunctionalInterface
public interface LineTransformer {
    String transform(String line);
}
