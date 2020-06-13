package com.github.jolice.game.listener.state.waiting.register;

import com.github.jolice.game.listener.state.waiting.WaitingStateListener;
import com.github.jolice.game.player.factory.GamePlayerFactory;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import com.github.jolice.game.player.GamePlayer;

@RequiredArgsConstructor
public class WaitingJoinRegistrationListener extends WaitingStateListener {

    private final GamePlayerStorage<? super GamePlayer> gamePlayerStorage;
    private final GamePlayerFactory<?> gamePlayerFactory;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        gamePlayerStorage.addPlayer(playerJoinEvent.getPlayer().getUniqueId(), this.gamePlayerFactory.createPlayer(playerJoinEvent.getPlayer()));
    }

}
