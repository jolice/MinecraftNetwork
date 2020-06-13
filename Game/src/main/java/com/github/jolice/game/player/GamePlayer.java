package com.github.jolice.game.player;

import org.bukkit.Location;

import java.util.UUID;

public interface GamePlayer {

    String getName();

    void teleport(Location location);

    GamePlayerStatus getStatus();

    GameScore getScore();

    UUID getId();

    GamePlayerKit getKit();
}
