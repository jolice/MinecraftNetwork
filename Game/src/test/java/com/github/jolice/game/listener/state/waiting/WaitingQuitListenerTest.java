package com.github.jolice.game.listener.state.waiting;

import com.github.jolice.game.config.GameOptions;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.Test;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.timer.Timer;

import java.util.Collection;

import static org.mockito.Mockito.*;

public class WaitingQuitListenerTest {

    @Test
    public void onQuit() {
        Timer timer = mock(Timer.class);
        GameOptions gameOptions = mock(GameOptions.class);
        Broadcast broadcast = mock(Broadcast.class);
        Server server = mock(Server.class);

        when(gameOptions.getMinimumPlayersToStart()).thenReturn(10);
        PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(mock(Player.class), "");
        WaitingQuitListener waitingQuitListener = new WaitingQuitListener(timer, gameOptions, broadcast, server);

        setOnlinePlayers(server, 11);
        waitingQuitListener.onQuit(playerQuitEvent);
        verify(timer, times(0)).stop();
        setOnlinePlayers(server, 9);
        waitingQuitListener.onQuit(playerQuitEvent);
        verify(timer, times(1)).stop();
    }

    private void setOnlinePlayers(Server server, int count) {
        when(server.getOnlinePlayers()).thenAnswer(invocationOnMock -> {
            Collection<? extends Player> players = mock(Collection.class);
            when(players.size()).thenReturn(count);
            return players;
        });
    }
}