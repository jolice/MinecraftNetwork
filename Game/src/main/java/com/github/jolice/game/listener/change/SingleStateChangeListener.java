package com.github.jolice.game.listener.change;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.github.jolice.game.core.GameState;
import com.github.jolice.game.event.GameStateChangeEvent;

public abstract class SingleStateChangeListener implements Listener {

    @EventHandler
    public void listen(GameStateChangeEvent gameStateChangeEvent) {
        if (gameStateChangeEvent.getTo() == getTriggerState()) {
            this.onGameStateChange(gameStateChangeEvent);
        }
    }

    abstract void onGameStateChange(GameStateChangeEvent gameStateChangeEvent);

    abstract GameState getTriggerState();

}
