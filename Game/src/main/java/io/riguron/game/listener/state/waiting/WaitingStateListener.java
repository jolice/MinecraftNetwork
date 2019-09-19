package io.riguron.game.listener.state.waiting;

import io.riguron.game.core.GameState;
import io.riguron.game.listener.state.StateDependentListener;
import io.riguron.game.core.GameState;
import io.riguron.game.listener.state.StateDependentListener;

import java.util.Arrays;
import java.util.List;

public class WaitingStateListener implements StateDependentListener {
    @Override
    public List<GameState> states() {
        return Arrays.asList(GameState.WAITING, GameState.COUNTDOWN);
    }
}
