package com.github.jolice.game.config.advanced;

import lombok.*;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode
@ToString
public class ItemOptions {

    /**
     * Whether players are able to drop items.
     */
    private boolean drop;

    /**
     * Whether players are able to pickup items.
     */
    private boolean pickup;

}
