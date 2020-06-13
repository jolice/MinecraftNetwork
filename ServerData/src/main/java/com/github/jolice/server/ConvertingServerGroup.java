package com.github.jolice.server;

import lombok.RequiredArgsConstructor;

/**
 * A ServerGroup implementation that transforms server's name to the name
 * of its group.
 */
@RequiredArgsConstructor
public class ConvertingServerGroup implements ServerGroup {

    private final String serverName;

    public ConvertingServerGroup(ServerName serverName) {
        this(serverName.get());
    }

    @Override
    public String getName() {
        int separatorIndex = serverName.indexOf('-');
        if (separatorIndex > 0) {
            return serverName.substring(0, separatorIndex);
        }
        return serverName;
    }
}
