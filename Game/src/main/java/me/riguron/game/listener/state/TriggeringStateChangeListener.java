package me.riguron.game.listener.state;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.riguron.game.listener.ListenerRegistrationService;
import me.riguron.game.event.GameStateChangeEvent;

@RequiredArgsConstructor
public class TriggeringStateChangeListener implements Listener {

    private final ListenerRegistrationService listenerRegistrationService;

    @EventHandler
    public void onGameStateChanged(GameStateChangeEvent gameStateChangeEvent) {
        listenerRegistrationService.gameStateChanged(gameStateChangeEvent.getTo());
    }
}
