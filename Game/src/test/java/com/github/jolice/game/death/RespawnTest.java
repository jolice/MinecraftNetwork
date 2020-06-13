package com.github.jolice.game.death;

import com.github.jolice.game.config.GameOptions;
import com.github.jolice.game.core.Game;
import com.github.jolice.game.map.GameMap;
import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.GamePlayerStatus;
import org.junit.Test;
import io.riguron.system.task.Task;
import io.riguron.system.task.TaskFactory;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RespawnTest {

    @Test
    public void markForRespawn() {

        Game game = mock(Game.class);
        GameMap gameMap = mock(GameMap.class);
        when(game.getMap()).thenReturn(gameMap);
        GameOptions gameOptions = mock(GameOptions.class);

        TaskFactory taskFactory = mock(TaskFactory.class);

        Respawn respawn = new Respawn(game, taskFactory, gameOptions);

        when(taskFactory.newSyncDelayedTask(any(Runnable.class), anyInt())).thenAnswer(invocationOnMock -> (Task) () -> {
            Runnable argument = invocationOnMock.getArgument(0);
            argument.run();
        });

        UUID uuid = UUID.randomUUID();
        GamePlayer gamePlayer = mock(GamePlayer.class);
        when(gamePlayer.getId()).thenReturn(uuid);
        GamePlayerStatus gamePlayerStatus = mock(GamePlayerStatus.class);
        when(gamePlayer.getStatus()).thenReturn(gamePlayerStatus);

        respawn.markForRespawn(gamePlayer);

        verify(gamePlayerStatus).setSpectator(eq(true));
        // task is executed...
        verify(gamePlayerStatus).setSpectator(eq(false));
        verify(gameMap).teleportPlayer(eq(uuid));
    }

}