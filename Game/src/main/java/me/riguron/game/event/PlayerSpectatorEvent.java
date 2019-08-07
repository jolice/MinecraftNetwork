package me.riguron.game.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.bukkit.entity.Player;

/**
 * Event called when a player is made a spectator or,
 * on the contrary, returned back from the spectator to the game.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerSpectatorEvent extends GameEvent {

    private Player player;

    /**
     * True if player is made a spectator, false if he is already
     * a spectator and now returned back to the game.
     */
    private boolean on;

}
