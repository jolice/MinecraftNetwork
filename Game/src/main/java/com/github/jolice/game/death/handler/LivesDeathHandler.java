package com.github.jolice.game.death.handler;

import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.status.PlayerLives;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.death.Respawn;

import java.util.UUID;

/**
 * Player drops out from the game when he runs out of lives.
 * If he still has one or move lives, he re-spawns, but loses
 * one life.
 */
@RequiredArgsConstructor
public class LivesDeathHandler implements PlayerDeathHandler {

    private final PlayerLives playerLives;
    private final GamePlayerStorage<?> gamePlayerStorage;
    private final Respawn respawn;

    @Override
    public void onPlayerDeath(UUID playerId) {
        GamePlayer player = gamePlayerStorage.getPlayer(playerId);
        if (playerLives.decrementLives(playerId) == 0) {
            player.getStatus().dropOut();
        } else {
            respawn.markForRespawn(player);
        }
    }
}
