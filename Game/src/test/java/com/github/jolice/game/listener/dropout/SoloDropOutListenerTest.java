package com.github.jolice.game.listener.dropout;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.event.PlayerDropOutEvent;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SoloDropOutListenerTest {

    @Test
    public void whenDropOutAndOnePlayerLeftThenEnd() {
        Game game = mock(Game.class);
        GamePlayerStorage gamePlayerStorage = mock(GamePlayerStorage.class);

        PlayerDropOutEvent playerDropOutEvent = new PlayerDropOutEvent(UUID.randomUUID());

        when(gamePlayerStorage.getAlivePlayerCount()).thenReturn(1L);

        SoloDropOutListener soloDropOutListener = new SoloDropOutListener(game, gamePlayerStorage);
        soloDropOutListener.onDropOut(playerDropOutEvent);

        verify(game).end();
    }
}