package me.riguron.game.core.decorator;

import me.riguron.game.core.Game;
import me.riguron.game.core.GameState;
import me.riguron.game.core.type.DelegatingGame;
import me.riguron.system.task.timer.Timer;

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
