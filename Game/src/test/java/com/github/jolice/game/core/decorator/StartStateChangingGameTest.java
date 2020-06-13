package com.github.jolice.game.core.decorator;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StartStateChangingGameTest {

    @Test
    public void start() {
        Game delegate = mock(Game.class);
        StartStateChangingGame startStateChangingGame = new StartStateChangingGame(delegate);
        startStateChangingGame.start();

        verify(delegate, times(0)).start();
        verify(delegate).setState(GameState.ACTIVE);
    }
}