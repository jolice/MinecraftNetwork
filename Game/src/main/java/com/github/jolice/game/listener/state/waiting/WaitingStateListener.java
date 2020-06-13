package com.github.jolice.game.listener.state.waiting;

import com.github.jolice.game.core.GameState;
import com.github.jolice.game.listener.state.StateDependentListener;

import java.util.Arrays;
import java.util.List;

public class WaitingStateListener implements StateDependentListener {
    @Override
    public List<GameState> states() {
        return Arrays.asList(GameState.WAITING, GameState.COUNTDOWN);
    }
}
