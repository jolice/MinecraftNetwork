package com.github.jolice.game.core.decorator;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import com.github.jolice.game.core.type.DelegatingGame;

/**
 * Represents a game that starts right after the waiting countdown
 * ends.
 */
public class ImmediateStartingGame extends DelegatingGame {

    public ImmediateStartingGame(Game delegate) {
        super(delegate);
    }

    @Override
    public void start() {
        super.start();
        super.setState(GameState.ACTIVE);
    }
}
