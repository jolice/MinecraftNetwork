package me.riguron.game.listener.change;

import lombok.RequiredArgsConstructor;
import me.riguron.game.core.GameState;
import me.riguron.game.event.GameStateChangeEvent;
import me.riguron.game.winner.WinningHandler;

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
