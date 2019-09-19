package io.riguron.game.listener.state.waiting;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import io.riguron.game.config.GameOptions;
import io.riguron.system.internalization.Message;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.timer.Timer;

@RequiredArgsConstructor
public class WaitingQuitListener extends WaitingStateListener {

    private final Timer gameStartingTimer;
    private final GameOptions gameOptions;
    private final Broadcast broadcast;
    private final Server server;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (server.getOnlinePlayers().size() < gameOptions.getMinimumPlayersToStart()) {
            gameStartingTimer.stop();
            broadcast.broadcast(new Message("game.start.not_enough_players"));
        }
    }


}
