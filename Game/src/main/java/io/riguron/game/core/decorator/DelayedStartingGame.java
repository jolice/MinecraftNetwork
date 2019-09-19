package io.riguron.game.core.decorator;

import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import io.riguron.game.core.type.DelegatingGame;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import io.riguron.game.core.type.DelegatingGame;
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
