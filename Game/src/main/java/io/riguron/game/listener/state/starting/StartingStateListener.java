package io.riguron.game.listener.state.starting;

import io.riguron.game.listener.state.StateDependentListener;
import io.riguron.game.core.GameState;
import io.riguron.game.listener.state.StateDependentListener;

import java.util.Collections;
import java.util.List;

public class StartingStateListener implements StateDependentListener {
    @Override
    public List<GameState> states() {
        return Collections.singletonList(GameState.STARTING);
    }
}
