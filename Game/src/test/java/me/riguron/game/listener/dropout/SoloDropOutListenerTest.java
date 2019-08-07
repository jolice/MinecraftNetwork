package me.riguron.game.listener.dropout;

import org.junit.Test;
import me.riguron.game.core.Game;
import me.riguron.game.event.PlayerDropOutEvent;
import me.riguron.game.player.repository.GamePlayerStorage;

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