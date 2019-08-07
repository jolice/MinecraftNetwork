package me.riguron.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import me.riguron.game.config.advanced.AdvancedGameOptions;
import me.riguron.game.listener.state.active.ActiveStateListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

@RequiredArgsConstructor
public class ClickListener extends ActiveStateListener {

    private final AdvancedGameOptions gameOptions;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!gameOptions.getPlayerOptions().isInventoryClick()) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        }
    }
}
