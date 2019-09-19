package io.riguron.game.listener.state.active;

import io.riguron.game.event.PlayerSpectatorEvent;
import io.riguron.game.player.spectator.Spectator;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.riguron.game.event.PlayerSpectatorEvent;
import io.riguron.game.player.spectator.Spectator;

@RequiredArgsConstructor
public class PlayerSpectatorListener implements Listener {

    private final Spectator spectator;

    @EventHandler
    public void onPlayerSetSpectator(PlayerSpectatorEvent playerSpectatorEvent) {
        spectator.set(playerSpectatorEvent.getPlayer(), playerSpectatorEvent.isOn());
    }
}
