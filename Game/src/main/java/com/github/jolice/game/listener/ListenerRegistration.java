package com.github.jolice.game.listener;

import com.github.jolice.game.core.GameState;
import lombok.Value;
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
