package me.riguron.game.listener.state.waiting.register;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import me.riguron.game.listener.state.waiting.WaitingStateListener;
import me.riguron.game.player.repository.GamePlayerStorage;

@RequiredArgsConstructor
public class WaitingQuitUnregisterListener extends WaitingStateListener {

    private final GamePlayerStorage<?> playerRepository;

    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent) {
        playerRepository.removePlayer(quitEvent.getPlayer().getUniqueId());
    }

}
