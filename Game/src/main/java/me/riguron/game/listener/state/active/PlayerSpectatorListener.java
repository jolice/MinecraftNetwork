package me.riguron.game.listener.state.active;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.riguron.game.event.PlayerSpectatorEvent;
import me.riguron.game.player.spectator.Spectator;

@RequiredArgsConstructor
public class PlayerSpectatorListener implements Listener {

    private final Spectator spectator;

    @EventHandler
    public void onPlayerSetSpectator(PlayerSpectatorEvent playerSpectatorEvent) {
        spectator.set(playerSpectatorEvent.getPlayer(), playerSpectatorEvent.isOn());
    }
}
