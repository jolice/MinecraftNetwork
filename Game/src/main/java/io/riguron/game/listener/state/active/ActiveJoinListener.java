package io.riguron.game.listener.state.active;

import io.riguron.game.player.spectator.Spectator;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import io.riguron.game.player.spectator.Spectator;

@RequiredArgsConstructor
public class ActiveJoinListener extends ActiveStateListener {

    private final Spectator spectator;

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        spectator.set(playerJoinEvent.getPlayer(), true);
    }
}
