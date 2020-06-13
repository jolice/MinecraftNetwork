package com.github.jolice.game.listener.state.starting;

import com.github.jolice.game.listener.state.StateDependentListener;
import com.github.jolice.game.core.GameState;

import java.util.Collections;
import java.util.List;

public class StartingStateListener implements StateDependentListener {
    @Override
    public List<GameState> states() {
        return Collections.singletonList(GameState.STARTING);
    }
}
