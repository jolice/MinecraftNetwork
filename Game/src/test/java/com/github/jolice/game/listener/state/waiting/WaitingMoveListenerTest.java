package com.github.jolice.game.listener.state.waiting;

import com.github.jolice.game.config.GameOptions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WaitingMoveListenerTest {

    @Test
    public void onPlayerMove() {

        GameOptions gameOptions = mock(GameOptions.class);
        Location lobby = mock(Location.class);
        when(gameOptions.getLobby()).thenReturn(lobby);
        WaitingMoveListener waitingMoveListener = new WaitingMoveListener(gameOptions);
        Player player = mock(Player.class);
        Location to = mock(Location.class);
        when(to.getY()).thenReturn(-1D);
        PlayerMoveEvent playerMoveEvent = new PlayerMoveEvent(player, mock(Location.class), to);
        waitingMoveListener.onPlayerMove(playerMoveEvent);

        verify(player).teleport(eq(lobby));
    }
}