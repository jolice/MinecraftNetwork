package com.github.jolice.game.timer;

import lombok.RequiredArgsConstructor;
import com.github.jolice.game.core.Game;
import io.riguron.system.internalization.Message;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.timer.CountdownTask;

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
