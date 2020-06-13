package com.github.jolice.game.core.decorator;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import org.junit.Test;
import io.riguron.system.task.timer.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DelayedStartingGameTest {

    @Test
    public void start() {

        Timer timer = mock(Timer.class);
        Game delegate = mock(Game.class);
        DelayedStartingGame delayedStartingGame = new DelayedStartingGame(delegate, timer);

        delayedStartingGame.start();

        verify(delegate).start();
        verify(delegate).setState(GameState.STARTING);
        verify(timer).start();
    }
}