package me.riguron.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import me.riguron.game.config.advanced.AdvancedGameOptions;
import me.riguron.game.listener.state.active.ActiveStateListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;

@RequiredArgsConstructor
public class BlockListener extends ActiveStateListener {

    private final AdvancedGameOptions advancedGameOptions;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent e) {
        if (!advancedGameOptions.getBlockOptions().isCanBreak()) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!advancedGameOptions.getBlockOptions().isCanPlace()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (!advancedGameOptions.getBlockOptions().isBlockPhysics()) {
            event.setCancelled(true);
        }
    }


}
