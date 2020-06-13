package com.github.jolice.game.death.handler;

import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.GamePlayerStatus;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.status.PlayerLives;
import com.github.jolice.game.death.Respawn;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LivesDeathHandlerTest {

    @Test
    public void onPlayerDeath() {
        PlayerLives playerLives = mock(PlayerLives.class);
        GamePlayerStorage storage = mock(GamePlayerStorage.class);
        Respawn respawn = mock(Respawn.class);
        LivesDeathHandler livesDeathHandler = new LivesDeathHandler(
                playerLives, storage, respawn
        );

        UUID uuid =UUID.randomUUID();
        when(playerLives.decrementLives(eq(uuid))).thenReturn(3);
        GamePlayer player = mock(GamePlayer.class);
        when(storage.getPlayer(eq(uuid))).thenReturn(player);

        GamePlayerStatus playerStatus = mock(GamePlayerStatus.class);
        when(player.getStatus()).thenReturn(playerStatus);

        livesDeathHandler.onPlayerDeath(uuid);
        verify(respawn).markForRespawn(eq(player));
        when(playerLives.decrementLives(eq(uuid))).thenReturn(0);
        livesDeathHandler.onPlayerDeath(uuid);
        verify(playerStatus).dropOut();
    }
}