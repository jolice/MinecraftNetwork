package me.riguron.bukkit.item.executable;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class ExecutableItemListener implements Listener {

    private final ExecutableItems items;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        items.dispatchClick(event.getPlayer(), event.getItem());
    }
}
