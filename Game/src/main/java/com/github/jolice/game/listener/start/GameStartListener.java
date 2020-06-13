package com.github.jolice.game.listener.start;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.event.GameStartEvent;
import lombok.RequiredArgsConstructor;
import io.riguron.system.task.timer.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class GameStartListener implements Listener {

    private final Timer gameEndingTimer;
    private final Game game;

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        gameEndingTimer.start();
        game.getMap().teleportPlayers();
    }

}
