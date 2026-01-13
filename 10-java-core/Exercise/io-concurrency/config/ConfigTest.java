package io_concurrency;

import java.nio.file.Path;

public class ConfigTest {

    public static void main(String[] args) {

        AppConfig config = new AppConfig("MyApp", "1.0", 100, 30000);
        ConfigManager.saveConfig(config, Path.of("config.ser"));

        AppConfig loaded = ConfigManager.loadConfig(Path.of("config.ser"));
        System.out.println(loaded.getLastLoaded());
    }
}
