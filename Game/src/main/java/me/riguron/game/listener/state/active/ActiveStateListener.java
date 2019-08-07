package me.riguron.game.listener.state.active;

import me.riguron.game.core.GameState;
import me.riguron.game.listener.state.StateDependentListener;

import java.util.Arrays;
import java.util.List;

public class ActiveStateListener implements StateDependentListener {

    @Override
    public List<GameState> states() {
        return Arrays.asList(GameState.STARTING, GameState.ACTIVE, GameState.END);
    }
}
