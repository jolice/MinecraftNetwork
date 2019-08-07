package me.riguron.game.core.decorator;

import org.junit.Test;
import me.riguron.game.core.Game;
import me.riguron.game.core.GameState;

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