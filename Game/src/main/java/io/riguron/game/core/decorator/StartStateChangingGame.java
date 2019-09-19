package io.riguron.game.core.decorator;

import io.riguron.game.core.Game;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import io.riguron.game.core.type.DelegatingGame;

public class StartStateChangingGame extends DelegatingGame {

    public StartStateChangingGame(Game delegate) {
        super(delegate);
    }

    @Override
    public void start() {
        super.setState(GameState.ACTIVE);
    }
}
