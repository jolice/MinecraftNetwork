package io.riguron.game.listener.state.waiting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class WaitingLobbyListener extends WaitingStateListener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        e.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onBlockSpread(BlockSpreadEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onPhysic(BlockPhysicsEvent e) {
        e.setCancelled(true);
    }

}
