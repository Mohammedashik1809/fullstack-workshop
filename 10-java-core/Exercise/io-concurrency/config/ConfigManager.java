package io_concurrency;

import java.io.*;
import java.nio.file.Path;

public class ConfigManager {

    public static void saveConfig(AppConfig config, Path file) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            out.writeObject(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AppConfig loadConfig(Path file) {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file.toFile()))) {
            return (AppConfig) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
