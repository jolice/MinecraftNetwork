package io.riguron.game.config.advanced;

import io.riguron.game.core.GameState;
import lombok.*;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode
@ToString
public class PlayerOptions {

    /**
     * Whether the players are able to move while the game
     * is starting (i.e when state of the game is STARTING)
     *
     * @see GameState
     */
    private boolean moveWhileStarting;

    /**
     * Whether the players can drag items in their inventories
     */
    private boolean inventoryClick;
}
