package com.github.jolice.game.death.handler;

import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Player has no second life and drops out from the game after the first death.
 */
@RequiredArgsConstructor
public class DropOutDeathHandler implements PlayerDeathHandler {

    private final GamePlayerStorage<?> gamePlayerStorage;

    @Override
    public void onPlayerDeath(UUID playerId) {
        gamePlayerStorage
                .getPlayer(playerId)
                .getStatus()
                .dropOut();
    }
}
