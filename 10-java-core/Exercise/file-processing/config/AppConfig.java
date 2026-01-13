package file_processing;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AppConfig {

    private final Properties properties = new Properties();
    private final Path configPath;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public AppConfig(Path configPath) {
        this.configPath = configPath;
        loadOrCreateDefaults();
    }

    private void loadOrCreateDefaults() {
        lock.writeLock().lock();
        try {
            if (Files.exists(configPath)) {
                reload();
            } else {
                setDefaults();
                save();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void setDefaults() {
        properties.setProperty("app.name", "MyApplication");
        properties.setProperty("app.version", "1.0.0");
        properties.setProperty("app.debug", "false");
        properties.setProperty("db.host", "localhost");
        properties.setProperty("db.port", "5432");
        properties.setProperty("db.name", "mydb");
        properties.setProperty("db.pool.size", "10");
        properties.setProperty("feature.darkMode", "true");
        properties.setProperty("feature.analytics", "false");
    }

    public String getString(String key, String defaultValue) {
        lock.readLock().lock();
        try {
            return properties.getProperty(key, defaultValue);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(getString(key, String.valueOf(defaultValue)));
    }

    public double getDouble(String key, double defaultValue) {
        try {
            return Double.parseDouble(getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setProperty(String key, String value) {
        lock.writeLock().lock();
        try {
            properties.setProperty(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void save() {
        lock.writeLock().lock();
        try (OutputStream out = Files.newOutputStream(configPath)) {
            properties.store(out, "Application Configuration");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save config", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void reload() {
        lock.writeLock().lock();
        try (InputStream in = Files.newInputStream(configPath)) {
            properties.clear();
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to reload config", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map<String, String> getPropertiesWithPrefix(String prefix) {
        lock.readLock().lock();
        try {
            Map<String, String> result = new HashMap<>();
            for (String key : properties.stringPropertyNames()) {
                if (key.startsWith(prefix)) {
                    result.put(key, properties.getProperty(key));
                }
            }
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }
}
