package io.riguron.game.death.handler;

import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.status.PlayerLives;
import lombok.RequiredArgsConstructor;
import io.riguron.game.death.Respawn;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.status.PlayerLives;

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
