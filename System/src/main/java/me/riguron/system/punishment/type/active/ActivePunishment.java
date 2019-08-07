package me.riguron.system.punishment.type.active;

import me.riguron.system.punishment.type.Punishment;

import java.util.UUID;

public interface ActivePunishment extends Punishment {

    /**
     * Removes an active punishment from a player.
     *
     * @param uuid uuid of the player to be excused
     */
    void excuse(UUID uuid);
}
