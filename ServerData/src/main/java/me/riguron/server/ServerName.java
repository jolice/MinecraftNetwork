package me.riguron.server;

import java.nio.file.Paths;

/**
 * Simple singleton holder of the server's name.
 */
public enum ServerName {

    INSTANCE;

    /**
     * Name is equal to the name of the directory which the server process is running in.
     */
    private final String name = Paths.get(System.getProperty("user.dir")).toFile().getAbsoluteFile().getName();

    public String get() {
        return name;
    }

}
