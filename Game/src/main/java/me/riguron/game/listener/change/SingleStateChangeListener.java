package me.riguron.game.listener.change;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.riguron.game.core.GameState;
import me.riguron.game.event.GameStateChangeEvent;

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
