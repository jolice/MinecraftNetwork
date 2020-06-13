package com.github.jolice.game.config.advanced;

import lombok.*;

@SuppressWarnings("unused")
@Getter
@EqualsAndHashCode
@ToString
public class DamageOptions {

    /**
     * Whether the player can be damaged in any way.
     */
    private boolean playerDamage;

    /**
     * Whether players a damaged by the falling
     */
    private boolean fallDamage;

}
