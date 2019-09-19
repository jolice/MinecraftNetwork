package io.riguron.game.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
