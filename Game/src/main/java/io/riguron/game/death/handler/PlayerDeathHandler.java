package io.riguron.game.death.handler;

import java.util.UUID;

/**
 * Represents a scenario that happens when a player dies.
 *
 * @see DropOutDeathHandler
 * @see LivesDeathHandler
 * @see RespawnDeathHandler
 */
public interface PlayerDeathHandler {

    /**
     * An action that happens on player's death.
     *
     * @param playerId id of player that dies
     */
    void onPlayerDeath(UUID playerId);
}
