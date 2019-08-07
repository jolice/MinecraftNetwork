package me.riguron.game.bootstrap;

import me.riguron.game.core.GameKind;

/**
 * This class is implemented by plugins of the concrete games to
 * provide the configuration for the game framework.
 */
public interface GameProvider {

    GameKind getKind();

}
