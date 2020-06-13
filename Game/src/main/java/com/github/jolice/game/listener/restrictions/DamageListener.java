package com.github.jolice.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import com.github.jolice.game.config.advanced.AdvancedGameOptions;
import com.github.jolice.game.listener.state.active.ActiveStateListener;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

@RequiredArgsConstructor
public class DamageListener extends ActiveStateListener {

    private final AdvancedGameOptions advancedGameOptions;

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {

        if (!advancedGameOptions.getDamageOptions().isFallDamage()) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL)
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!advancedGameOptions.getDamageOptions().isPlayerDamage()) {
            if (e.getDamager().getType() == EntityType.PLAYER && e.getEntity().getType() == EntityType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }


}
