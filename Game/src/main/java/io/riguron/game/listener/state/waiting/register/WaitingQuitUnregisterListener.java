package io.riguron.game.listener.state.waiting.register;

import io.riguron.game.listener.state.waiting.WaitingStateListener;
import io.riguron.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import io.riguron.game.listener.state.waiting.WaitingStateListener;
import io.riguron.game.player.repository.GamePlayerStorage;

@RequiredArgsConstructor
public class WaitingQuitUnregisterListener extends WaitingStateListener {

    private final GamePlayerStorage<?> playerRepository;

    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent) {
        playerRepository.removePlayer(quitEvent.getPlayer().getUniqueId());
    }

}
