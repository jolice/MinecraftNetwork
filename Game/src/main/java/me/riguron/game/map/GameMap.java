package me.riguron.game.map;

import java.util.UUID;

public interface GameMap {

    String getName();

    /**
     * Teleports all online players to the locations of this map.
     */
    void teleportPlayers();

    /**
     * Teleports single player to the one of map locations (picked randomly).
     *
     * @param playerId id of the players for the teleport.
     */
    void teleportPlayer(UUID playerId);
}
