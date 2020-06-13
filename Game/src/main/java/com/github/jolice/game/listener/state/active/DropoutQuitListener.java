package com.github.jolice.game.listener.state.active;

import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class DropoutQuitListener extends ActiveStateListener {

    private final GamePlayerStorage<?> playerStorage;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
            playerStorage
                    .getPlayer(event.getPlayer().getUniqueId())
                    .getStatus()
                    .dropOut();
    }

}
