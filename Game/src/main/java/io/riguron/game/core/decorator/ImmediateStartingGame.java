package io.riguron.game.core.decorator;

import io.riguron.game.core.Game;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import io.riguron.game.core.type.DelegatingGame;

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
