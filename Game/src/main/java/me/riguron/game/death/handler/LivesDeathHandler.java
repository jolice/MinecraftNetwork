package me.riguron.game.death.handler;

import lombok.RequiredArgsConstructor;
import me.riguron.game.death.Respawn;
import me.riguron.game.player.GamePlayer;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.status.PlayerLives;

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
