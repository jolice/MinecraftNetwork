package me.riguron.game.timer;

import lombok.RequiredArgsConstructor;
import me.riguron.game.core.Game;
import me.riguron.system.internalization.Message;
import me.riguron.system.stream.Broadcast;
import me.riguron.system.task.timer.CountdownTask;

/**
 * Waiting countdown.
 */
@RequiredArgsConstructor
public class GameStartingCountdown implements CountdownTask {

    private final Game game;
    private final Broadcast broadcast;

    @Override
    public void onTick(long tick) {
        broadcast.broadcast(new Message("game.state.ready", tick));
    }

    @Override
    public void complete() {
        this.game.start();
    }
}
