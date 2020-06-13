package com.github.jolice.game.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BasicGamePlayer implements GamePlayer {

    private final Player player;

    private final GamePlayerStatus gamePlayerStatus;

    private final GameScore gameScore = new GameScore();

    private final GamePlayerKit gamePlayerKit = new GamePlayerKit();

    public BasicGamePlayer(Player player) {
        this.player = player;
        this.gamePlayerStatus = new BasicPlayerStatus(player);
    }


    public String getName() {
        return player.getName();
    }

    public void teleport(Location location) {
        player.teleport(location);
    }


    public GamePlayerStatus getStatus() {
        return gamePlayerStatus;
    }

    public GameScore getScore() {
        return gameScore;
    }

    public UUID getId() {
        return player.getUniqueId();
    }

    public GamePlayerKit getKit() {
        return gamePlayerKit;
    }

}
