package com.github.jolice.game.bootstrap;

import com.github.jolice.game.core.GameKind;

/**
 * This class is implemented by plugins of the concrete games to
 * provide the configuration for the game framework.
 */
public interface GameProvider {

    GameKind getKind();

}
