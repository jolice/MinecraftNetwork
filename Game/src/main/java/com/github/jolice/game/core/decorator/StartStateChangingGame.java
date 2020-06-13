package com.github.jolice.game.core.decorator;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import com.github.jolice.game.core.type.DelegatingGame;

public class StartStateChangingGame extends DelegatingGame {

    public StartStateChangingGame(Game delegate) {
        super(delegate);
    }

    @Override
    public void start() {
        super.setState(GameState.ACTIVE);
    }
}
