package com.github.jolice.game.listener.state.active;

import com.github.jolice.game.listener.state.StateDependentListener;
import com.github.jolice.game.core.GameState;

import java.util.Arrays;
import java.util.List;

public class ActiveStateListener implements StateDependentListener {

    @Override
    public List<GameState> states() {
        return Arrays.asList(GameState.STARTING, GameState.ACTIVE, GameState.END);
    }
}
