package io.riguron.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import io.riguron.game.config.advanced.AdvancedGameOptions;
import io.riguron.game.listener.state.active.ActiveStateListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

@RequiredArgsConstructor
public class SpawnListener extends ActiveStateListener {

    private final AdvancedGameOptions advancedGameOptions;

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        if (!advancedGameOptions.getChangingOptions().isEntitySpawn()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e) {
        e.setCancelled(true);
    }

}
