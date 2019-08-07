package me.riguron.game.listener.start;

import lombok.RequiredArgsConstructor;
import me.riguron.game.core.Game;
import me.riguron.game.event.GameStartEvent;
import me.riguron.system.task.timer.Timer;
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
