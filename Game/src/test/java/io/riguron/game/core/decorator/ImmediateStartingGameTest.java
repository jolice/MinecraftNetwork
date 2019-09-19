package io.riguron.game.core.decorator;

import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import org.junit.Test;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ImmediateStartingGameTest {

    @Test
    public void start() {

        Game delegate = mock(Game.class);
        ImmediateStartingGame immediateStartingGame = new ImmediateStartingGame(delegate);
        immediateStartingGame.start();

        verify(delegate).start();
        verify(delegate).setState(GameState.ACTIVE);



    }
}