package me.riguron.game.death.handler;

import me.riguron.game.death.Respawn;
import me.riguron.game.player.GamePlayer;
import me.riguron.game.player.GamePlayerStatus;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.status.PlayerLives;
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