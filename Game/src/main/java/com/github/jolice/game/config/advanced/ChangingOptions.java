package com.github.jolice.game.config.advanced;

import lombok.*;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode
@ToString
public class ChangingOptions {

    /**
     * Whether the weather changes (for example, may the snow or the rain occur)
     */
    private boolean weatherChange;

    /**
     * Whether the players' food level changes, i.e players feel hunger.
     */
    private boolean foodChange;

    /**
     * Whether the time changes (or always stays at the fixed value).
     */
    private boolean timeChange;

    /**
     * Whether the mobs spawn.
     */
    private boolean entitySpawn;

}
