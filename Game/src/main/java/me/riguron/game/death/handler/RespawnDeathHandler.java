package me.riguron.game.death.handler;

import lombok.RequiredArgsConstructor;
import me.riguron.game.death.Respawn;
import me.riguron.game.player.repository.GamePlayerStorage;

import java.util.UUID;

/**
 * Player actually never drops out from the game, he just re-spawns.
 */
@RequiredArgsConstructor
public class RespawnDeathHandler implements PlayerDeathHandler {

    private final Respawn respawn;
    private final GamePlayerStorage<?> gamePlayerStorage;

    @Override
    public void onPlayerDeath(UUID uuid) {
        this.respawn.markForRespawn(gamePlayerStorage.getPlayer(uuid));
    }
}
