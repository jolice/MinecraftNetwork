package com.github.jolice.game.config.advanced;

import lombok.*;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode
@ToString
public class BlockOptions {

    /**
     * Whether players are allowed to place blocks.
     */
    private boolean canPlace;

    /**
     * Whether players are allowed to destroy blocks.
     */
    private boolean canBreak;

    /**
     * Whether block physics (for example, sand falling) may occur
     */
    private boolean blockPhysics;

}
