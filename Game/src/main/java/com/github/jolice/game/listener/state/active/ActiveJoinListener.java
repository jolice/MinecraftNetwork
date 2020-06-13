package com.github.jolice.game.listener.state.active;

import com.github.jolice.game.player.spectator.Spectator;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class ActiveJoinListener extends ActiveStateListener {

    private final Spectator spectator;

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        spectator.set(playerJoinEvent.getPlayer(), true);
    }
}
