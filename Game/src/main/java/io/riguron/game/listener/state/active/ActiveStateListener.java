package io.riguron.game.listener.state.active;

import io.riguron.game.listener.state.StateDependentListener;
import io.riguron.game.core.GameState;
import io.riguron.game.listener.state.StateDependentListener;

import java.util.Arrays;
import java.util.List;

public class ActiveStateListener implements StateDependentListener {

    @Override
    public List<GameState> states() {
        return Arrays.asList(GameState.STARTING, GameState.ACTIVE, GameState.END);
    }
}
