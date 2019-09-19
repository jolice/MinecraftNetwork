package io.riguron.game.death.handler;

import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.GamePlayerStatus;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.GamePlayerStatus;
import io.riguron.game.player.repository.GamePlayerStorage;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class DropOutDeathHandlerTest {

    @Test
    public void onPlayerDeath() {

        GamePlayerStorage<? super GamePlayer> storage = mock(GamePlayerStorage.class);
        DropOutDeathHandler dropOutDeathHandler = new DropOutDeathHandler(storage);
        UUID uuid = UUID.randomUUID();
        GamePlayer gamePlayer = mock(GamePlayer.class);
        GamePlayerStatus gamePlayerStatus = mock(GamePlayerStatus.class);
        when(gamePlayer.getStatus()).thenReturn(gamePlayerStatus);
        when(storage.getPlayer(uuid)).thenReturn(gamePlayer);

        dropOutDeathHandler.onPlayerDeath(uuid);

        verify(gamePlayerStatus).dropOut();
    }
}