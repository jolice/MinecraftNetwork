package io.riguron.bukkit.listener.stream;

import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerLeaveHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import io.riguron.system.player.PlayerProfileRepository;

@RequiredArgsConstructor
public class DisconnectListener implements Listener {

    private final PlayerLeaveHandler leaveHandler;

    @EventHandler
    public void onLeave(PlayerQuitEvent playerQuitEvent) {
       leaveHandler.onLeave(playerQuitEvent.getPlayer().getUniqueId());
    }
}
