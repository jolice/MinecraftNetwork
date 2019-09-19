package io.riguron.game.listener.state.waiting;

import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.server.OnlineIndexer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class WaitingOnlineIndexer extends WaitingStateListener {

    private final OnlineIndexer onlineIndexer;

    @EventHandler
    public void join(PlayerJoinEvent e) {
        onlineIndexer.increment();
    }

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        onlineIndexer.decrement();
    }
}
