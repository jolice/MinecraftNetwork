package me.riguron.game.listener.state.waiting;

import lombok.RequiredArgsConstructor;
import me.riguron.bukkit.item.join.JoinItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class WaitingItemsListener extends WaitingStateListener {

    private final JoinItems joinItems;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        joinItems.giveTo(event.getPlayer());
    }
}
