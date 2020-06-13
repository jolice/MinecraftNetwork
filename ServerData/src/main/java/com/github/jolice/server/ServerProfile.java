package com.github.jolice.server;

import lombok.Value;

/**
 * Class that pairs group and name of the server.
 */
@Value
public class ServerProfile {

    private final ServerGroup serverGroup;
    private final ServerName serverName;

    public final String getServerName() {
        return serverName.get();
    }
}
