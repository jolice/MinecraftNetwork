package com.github.jolice.game.listener.state.waiting;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import com.github.jolice.game.config.GameOptions;
import io.riguron.system.task.timer.Timer;

@RequiredArgsConstructor
public class WaitingJoinListener extends WaitingStateListener {

    private final Timer gameStartingTimer;
    private final GameOptions gameOptions;
    private final Server server;

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        playerJoinEvent.getPlayer().teleport(gameOptions.getLobby());
        this.checkForStart();
    }

    private void checkForStart() {
        if (server.getOnlinePlayers().size() == gameOptions.getMinimumPlayersToStart()) {
            gameStartingTimer.start();
        }
    }

}
