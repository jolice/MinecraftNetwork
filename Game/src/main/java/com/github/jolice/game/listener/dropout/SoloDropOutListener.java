package com.github.jolice.game.listener.dropout;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.github.jolice.game.event.PlayerDropOutEvent;

@RequiredArgsConstructor
public class SoloDropOutListener implements Listener {

    private final Game game;
    private final GamePlayerStorage<?> gamePlayerStorage;

    @EventHandler
    public void onDropOut(PlayerDropOutEvent event) {
        if (gamePlayerStorage.getAlivePlayerCount() == 1) {
            game.end();
        }
    }
}
