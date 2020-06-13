package com.github.jolice.game.death.handler;

import com.github.jolice.game.death.Respawn;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;

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
