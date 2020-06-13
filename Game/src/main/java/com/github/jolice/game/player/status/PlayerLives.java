package com.github.jolice.game.player.status;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Container class storing data for players' lives.
 */
public class PlayerLives {

    private static final int TOTAL_LIVES = 5;

    private Map<UUID, Integer> livesData = new HashMap<>();

    public Integer getPlayerLives(UUID uuid) {
        return livesData.computeIfAbsent(uuid, x -> TOTAL_LIVES);
    }

    public Integer decrementLives(UUID uuid) {
        return livesData.merge(uuid, TOTAL_LIVES - 1, (x, y) -> x - 1);
    }

}
