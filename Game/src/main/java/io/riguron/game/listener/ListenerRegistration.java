package io.riguron.game.listener;

import lombok.Value;
import io.riguron.game.core.GameState;
import org.bukkit.event.Listener;

import java.util.List;

@Value
public class ListenerRegistration {

    /**
     * The native bukkit listener.
     */
    private Listener listener;

    /**
     * States during which the listener is active.
     */
    private List<GameState> states;

}
