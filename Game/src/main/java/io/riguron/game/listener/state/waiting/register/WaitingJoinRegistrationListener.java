package io.riguron.game.listener.state.waiting.register;

import io.riguron.game.listener.state.waiting.WaitingStateListener;
import io.riguron.game.player.factory.GamePlayerFactory;
import io.riguron.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import io.riguron.game.listener.state.waiting.WaitingStateListener;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.factory.GamePlayerFactory;
import io.riguron.game.player.repository.GamePlayerStorage;

@RequiredArgsConstructor
public class WaitingJoinRegistrationListener extends WaitingStateListener {

    private final GamePlayerStorage<? super GamePlayer> gamePlayerStorage;
    private final GamePlayerFactory<?> gamePlayerFactory;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        gamePlayerStorage.addPlayer(playerJoinEvent.getPlayer().getUniqueId(), this.gamePlayerFactory.createPlayer(playerJoinEvent.getPlayer()));
    }

}
