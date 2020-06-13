package com.github.jolice.game.listener.state.active;

import com.github.jolice.game.event.PlayerSpectatorEvent;
import com.github.jolice.game.player.spectator.Spectator;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class PlayerSpectatorListener implements Listener {

    private final Spectator spectator;

    @EventHandler
    public void onPlayerSetSpectator(PlayerSpectatorEvent playerSpectatorEvent) {
        spectator.set(playerSpectatorEvent.getPlayer(), playerSpectatorEvent.isOn());
    }
}
