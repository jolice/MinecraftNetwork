package com.github.jolice.server.type;

import lombok.Data;

/**
 * Represents plain server, for example, lobby.
 */
@Data
public class Server {

    private String name;
    private boolean enabled;

    /**
     * Current count of players on the server.
     */
    private int onlinePlayers;

    /**
     * Count of slots.
     */
    private int maxPlayers;

    public Server(String name, boolean enabled, int onlinePlayers, int maxPlayers) {
        this.name = name;
        this.enabled = enabled;
        this.onlinePlayers = onlinePlayers;
        this.maxPlayers = maxPlayers;
    }

}
