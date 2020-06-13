package com.github.jolice.game.core;

public enum GameDeathType {

    /**
     * The player does not actually die, he just gets dropped out
     * for a short period of time and then joins game again. This is
     * called "respawn".
     */
    RESPAWN,

    /**
     * The player has only one life. Once he dies, he gets dropped out from the
     * game and does not participate in it anymore.
     */
    DROP_OUT,

    /**
     * This is similar to DROP_OUT, but the player has multiple lives "in stock".
     * He completely dies when he runs out of lives.
     */
    LIVES
}
