package me.riguron.game.listener.state.active;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import me.riguron.game.player.spectator.Spectator;

@RequiredArgsConstructor
public class ActiveJoinListener extends ActiveStateListener {

    private final Spectator spectator;

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        spectator.set(playerJoinEvent.getPlayer(), true);
    }
}
