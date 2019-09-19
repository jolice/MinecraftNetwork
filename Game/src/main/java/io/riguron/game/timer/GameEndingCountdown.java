package io.riguron.game.timer;

import lombok.RequiredArgsConstructor;
import io.riguron.game.core.Game;
import io.riguron.system.internalization.Message;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.timer.CountdownTask;

@RequiredArgsConstructor
public class GameEndingCountdown implements CountdownTask {

    private final Game game;
    private final Broadcast broadcast;

    @Override
    public void onTick(long tick) {
        if (tick % 30 == 0 || tick <= 10) {
            broadcast.broadcast(new Message("game.state.ending", tick));
        }
    }

    @Override
    public void complete() {
        game.end();
    }
}
