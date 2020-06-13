package com.github.jolice.game.listener.state.starting;

import lombok.RequiredArgsConstructor;
import com.github.jolice.game.config.advanced.AdvancedGameOptions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public class StartingBlockingListener extends StartingStateListener {


    private final AdvancedGameOptions gameOptions;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        if (!gameOptions.getPlayerOptions().isMoveWhileStarting()) {
            playerMoveEvent.setCancelled(true);
        }
    }

}
