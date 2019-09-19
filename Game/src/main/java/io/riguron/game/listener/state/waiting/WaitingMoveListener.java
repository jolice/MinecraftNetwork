package io.riguron.game.listener.state.waiting;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import io.riguron.game.config.GameOptions;

@RequiredArgsConstructor
public class WaitingMoveListener extends WaitingStateListener {

    private final GameOptions gameOptions;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        if (playerMoveEvent.getTo().getY() < 0) {
            playerMoveEvent.getPlayer().teleport(gameOptions.getLobby());
        }
    }
}
