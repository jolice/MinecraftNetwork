package io.riguron.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import io.riguron.game.config.advanced.AdvancedGameOptions;
import io.riguron.game.listener.state.active.ActiveStateListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

@RequiredArgsConstructor
public class ItemListener extends ActiveStateListener {

    private final AdvancedGameOptions advancedGameOptions;

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!advancedGameOptions.getItemOptions().isDrop()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (!advancedGameOptions.getItemOptions().isPickup()) {
            e.setCancelled(true);
        }
    }

}
