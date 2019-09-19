package io.riguron.game.listener.state;

import io.riguron.game.event.GameStateChangeEvent;
import io.riguron.game.listener.ListenerRegistrationService;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.riguron.game.listener.ListenerRegistrationService;
import io.riguron.game.event.GameStateChangeEvent;

@RequiredArgsConstructor
public class TriggeringStateChangeListener implements Listener {

    private final ListenerRegistrationService listenerRegistrationService;

    @EventHandler
    public void onGameStateChanged(GameStateChangeEvent gameStateChangeEvent) {
        listenerRegistrationService.gameStateChanged(gameStateChangeEvent.getTo());
    }
}
