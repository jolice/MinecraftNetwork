package com.github.jolice.game.listener.state.active;

import com.github.jolice.game.player.repository.GamePlayerStorage;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;
import java.util.function.Function;

public class SpectatorActionListener extends ActiveStateListener {

    private GamePlayerStorage<?> gamePlayerStorage;

    public SpectatorActionListener(GamePlayerStorage<?> gamePlayerStorage) {
        this.gamePlayerStorage = gamePlayerStorage;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        this.cancelForSpectator(e);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent e) {
        this.cancelForSpectator(e);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDrop(PlayerDropItemEvent e) {
        this.cancelForSpectator(e);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPickup(EntityPickupItemEvent e) {
        this.cancelForSpectator(e);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e) {
        this.cancelForSpectator(e, BlockBreakEvent::getPlayer);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e) {
        this.cancelForSpectator(e, BlockPlaceEvent::getPlayer);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        this.cancelForSpectator(e, InventoryInteractEvent::getWhoClicked);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        this.cancelForSpectator(e, PlayerEvent::getPlayer);
    }

    private <T extends Event & Cancellable> void cancelForSpectator(T cancellable, Function<T, Entity> eventToPlayerMapper) {
        if (isSpectator(eventToPlayerMapper.apply(cancellable).getUniqueId())) {
            cancellable.setCancelled(true);
        }
    }

    /**
     * Checks whether the player is spectator. Player can be a
     * spectator either he is explicitly marked as a spectator
     * or if he is not present in the storage (because he joined
     * after the beginning of the game)
     *
     * @param uuid uuid of the player
     * @return whether the player with specified UUID is a spectator
     */
    private boolean isSpectator(UUID uuid) {
        return !gamePlayerStorage.contains(uuid) || gamePlayerStorage.getPlayer(uuid).getStatus().isSpectator();
    }

    private <T extends PlayerEvent & Cancellable> void cancelForSpectator(T cancellablePlayerEvent) {
        this.cancelForSpectator(cancellablePlayerEvent, PlayerEvent::getPlayer);
    }

    private <T extends EntityEvent & Cancellable> void cancelForSpectator(T cancellableEntityEvent) {
        this.cancelForSpectator(cancellableEntityEvent, EntityEvent::getEntity);
    }

}
