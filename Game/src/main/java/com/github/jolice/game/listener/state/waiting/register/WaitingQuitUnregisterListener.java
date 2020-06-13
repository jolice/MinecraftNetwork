package com.github.jolice.game.listener.state.waiting.register;

import com.github.jolice.game.listener.state.waiting.WaitingStateListener;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class WaitingQuitUnregisterListener extends WaitingStateListener {

    private final GamePlayerStorage<?> playerRepository;

    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent) {
        playerRepository.removePlayer(quitEvent.getPlayer().getUniqueId());
    }

}
