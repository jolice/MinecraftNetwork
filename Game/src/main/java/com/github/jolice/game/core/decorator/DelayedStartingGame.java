package com.github.jolice.game.core.decorator;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import com.github.jolice.game.core.type.DelegatingGame;
import io.riguron.system.task.timer.Timer;

/**
 * Represents a game that starts in a fixed delay after a waiting countdown end.
 */
public class DelayedStartingGame extends DelegatingGame {

    private Timer startingTimer;

    public DelayedStartingGame(Game delegate, Timer startingTimer) {
        super(delegate);
        this.startingTimer = startingTimer;
    }

    @Override
    public void start() {
        super.start();
        this.setState(GameState.STARTING);
        this.startingTimer.start();
    }

}
