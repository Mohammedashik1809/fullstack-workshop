package io_concurrency;

import java.io.*;
import java.time.LocalDateTime;

public class AppConfig implements Serializable {

    private String appName;
    private String version;
    private int maxConnections;
    private int timeout;

    private transient LocalDateTime lastLoaded;

    public AppConfig(String appName, String version, int maxConnections, int timeout) {
        this.appName = appName;
        this.version = version;
        this.maxConnections = maxConnections;
        this.timeout = timeout;
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        lastLoaded = LocalDateTime.now();
    }

    public LocalDateTime getLastLoaded() {
        return lastLoaded;
    }
}
