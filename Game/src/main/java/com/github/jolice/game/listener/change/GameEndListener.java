package com.github.jolice.game.listener.change;

import com.github.jolice.game.winner.WinningHandler;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.core.GameState;
import com.github.jolice.game.event.GameStateChangeEvent;

@RequiredArgsConstructor
public class GameEndListener extends SingleStateChangeListener {

    private final WinningHandler winningHandler;

    @Override
    protected void onGameStateChange(GameStateChangeEvent gameStateChangeEvent) {
        winningHandler.gameFinished();
    }

    @Override
    protected GameState getTriggerState() {
        return GameState.END;
    }
}
