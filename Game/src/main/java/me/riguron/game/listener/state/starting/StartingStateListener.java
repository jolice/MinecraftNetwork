package me.riguron.game.listener.state.starting;

import me.riguron.game.core.GameState;
import me.riguron.game.listener.state.StateDependentListener;

import java.util.Collections;
import java.util.List;

public class StartingStateListener implements StateDependentListener {
    @Override
    public List<GameState> states() {
        return Collections.singletonList(GameState.STARTING);
    }
}
