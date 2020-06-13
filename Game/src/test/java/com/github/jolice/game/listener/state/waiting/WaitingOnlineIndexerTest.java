package com.github.jolice.game.listener.state.waiting;

import com.github.jolice.game.config.GameOptions;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.Before;
import org.junit.Test;
import io.riguron.system.task.timer.Timer;

import java.util.Collection;

import static org.mockito.Mockito.*;

public class WaitingOnlineIndexerTest {

    private static final int MIN_PLAYERS_TO_START = 10;

    private Server server;
    private Timer timer;
    private GameOptions gameOptions;
    private WaitingJoinListener waitingJoinListener;

    @Before
    public void setUp() throws Exception {
        server = mock(Server.class);
        timer = mock(Timer.class);
        gameOptions = mock(GameOptions.class);
        when(gameOptions.getMinimumPlayersToStart()).thenReturn(MIN_PLAYERS_TO_START);
        waitingJoinListener = new WaitingJoinListener(timer,
                gameOptions,
                server);
    }

    @Test
    public void whenJoinThenTeleported() {

        Player player = mock(Player.class);
        PlayerJoinEvent event = new PlayerJoinEvent(player, "");
        Location teleport = mock(Location.class);
        when(gameOptions.getLobby()).thenReturn(teleport);
        waitingJoinListener.onJoin(event);
        verify(player).teleport(eq(teleport));
    }

    @Test
    public void whenNotEnoughPlayersThenNotStarted() {
        PlayerJoinEvent event = new PlayerJoinEvent(mock(Player.class), "");
        waitingJoinListener.onJoin(event);
        verify(timer, times(0)).start();
    }

    @Test
    public void whenEnoughPlayersThenTimerStarted() {
        when(server.getOnlinePlayers()).thenAnswer(invocationOnMock -> {
            Collection<? extends Player> players = mock(Collection.class);
            when(players.size()).thenReturn(MIN_PLAYERS_TO_START);
            return players;
        });

        PlayerJoinEvent event = new PlayerJoinEvent(mock(Player.class), "");

        waitingJoinListener.onJoin(event);
        verify(timer, times(1)).start();

    }
}