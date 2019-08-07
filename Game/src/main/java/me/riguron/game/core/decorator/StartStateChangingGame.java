package me.riguron.game.core.decorator;

import me.riguron.game.core.Game;
import me.riguron.game.core.GameState;
import me.riguron.game.core.type.DelegatingGame;

public class StartStateChangingGame extends DelegatingGame {

    public StartStateChangingGame(Game delegate) {
        super(delegate);
    }

    @Override
    public void start() {
        super.setState(GameState.ACTIVE);
    }
}
