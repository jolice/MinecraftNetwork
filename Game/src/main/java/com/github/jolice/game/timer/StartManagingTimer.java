package com.github.jolice.game.timer;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import lombok.RequiredArgsConstructor;
import io.riguron.system.task.timer.Timer;

@RequiredArgsConstructor
public class StartManagingTimer implements Timer {

    private final Game game;
    private final Timer delegate;

    @Override
    public void start() {
        game.setState(GameState.COUNTDOWN);
        delegate.start();
    }

    @Override
    public void stop() {
        game.setState(GameState.WAITING);
        delegate.stop();
    }
}
