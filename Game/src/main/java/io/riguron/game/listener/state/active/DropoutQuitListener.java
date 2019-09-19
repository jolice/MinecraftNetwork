package io.riguron.game.listener.state.active;

import io.riguron.game.player.repository.GamePlayerStorage;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import io.riguron.game.player.repository.GamePlayerStorage;

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
