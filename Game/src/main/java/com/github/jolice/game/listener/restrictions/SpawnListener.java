package com.github.jolice.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import com.github.jolice.game.config.advanced.AdvancedGameOptions;
import com.github.jolice.game.listener.state.active.ActiveStateListener;
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
