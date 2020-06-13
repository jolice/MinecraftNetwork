package com.github.jolice.game.core.decorator;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import org.junit.Test;

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