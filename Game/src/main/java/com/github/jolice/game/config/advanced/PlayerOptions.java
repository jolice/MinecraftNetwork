package com.github.jolice.game.config.advanced;

import com.github.jolice.game.core.GameState;
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
