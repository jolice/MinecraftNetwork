package com.github.jolice.game.listener.state;

import com.github.jolice.game.event.GameStateChangeEvent;
import com.github.jolice.game.listener.ListenerRegistrationService;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class TriggeringStateChangeListener implements Listener {

    private final ListenerRegistrationService listenerRegistrationService;

    @EventHandler
    public void onGameStateChanged(GameStateChangeEvent gameStateChangeEvent) {
        listenerRegistrationService.gameStateChanged(gameStateChangeEvent.getTo());
    }
}
