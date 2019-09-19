package io.riguron.game.listener.change;

import io.riguron.game.winner.WinningHandler;
import lombok.RequiredArgsConstructor;
import io.riguron.game.core.GameState;
import io.riguron.game.event.GameStateChangeEvent;
import io.riguron.game.winner.WinningHandler;

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
