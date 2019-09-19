package io.riguron.game.bootstrap;

import io.riguron.game.core.GameKind;

/**
 * This class is implemented by plugins of the concrete games to
 * provide the configuration for the game framework.
 */
public interface GameProvider {

    GameKind getKind();

}
