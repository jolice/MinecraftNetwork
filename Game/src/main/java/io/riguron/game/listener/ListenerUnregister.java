package io.riguron.game.listener;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class ListenerUnregister {

    public void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}
