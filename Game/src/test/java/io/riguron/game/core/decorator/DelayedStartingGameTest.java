package io.riguron.game.core.decorator;

import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import org.junit.Test;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
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