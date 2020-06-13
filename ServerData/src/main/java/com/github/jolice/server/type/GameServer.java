package com.github.jolice.server.type;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Represents standard Game server.
 */
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(callSuper = true)
@Data
public class GameServer extends Server {

    private String map;
    private String gameName;

    /**
     * Whether the game is active, i.e other players can join.
     */
    private boolean active;

    public GameServer(Server server, String map, String gameName, boolean active) {
        super(server.getName(), server.isEnabled(), server.getOnlinePlayers(), server.getMaxPlayers());
        this.map = map;
        this.gameName = gameName;
        this.active = active;
    }

}
