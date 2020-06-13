package com.github.jolice.game.player.repository;

import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.GamePlayerStatus;
import com.github.jolice.game.player.NullPlayer;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VirtualGamePlayerStorageTest {

    private VirtualGamePlayerStorage<GamePlayer> storage = new VirtualGamePlayerStorage<>(new NullPlayer());

    @Test
    public void whenPlayerExistThenReturnedByGet() {
        GamePlayer gamePlayer = putPlayer();
        assertSame(gamePlayer, storage.getPlayer(gamePlayer.getId()));
    }

    @Test
    public void whenPlayerDoesNotExistThenNullReturned() {
        assertEquals(NullPlayer.class, storage.getPlayer(UUID.randomUUID()).getClass());
    }

    @Test
    public void whenRemovePlayerThenReturned() {
        GamePlayer newPlayer = putPlayer();
        assertSame(newPlayer, storage.getPlayer(newPlayer.getId()));
        storage.removePlayer(newPlayer.getId());
        assertEquals(NullPlayer.class, storage.getPlayer(newPlayer.getId()).getClass());
    }

    @Test
    public void whenGetAllPlayersThenReturnedAllPlayers() {
        Set<GamePlayer> playerSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            playerSet.add(putPlayer());
        }

        assertEquals(playerSet, new HashSet<>(storage.getAllPlayers()));
    }

    @Test
    public void whenGetAlivePlayers() {
        for (int i = 0; i < 10; i++) {
            putPlayer(i % 2 == 0);
        }
        assertEquals(5, storage.getAlivePlayerCount());
    }

    private GamePlayer putPlayer() {
        return putPlayer(true);
    }

    private GamePlayer putPlayer(boolean alive) {
        UUID uuid = UUID.randomUUID();
        GamePlayer gamePlayer = mock(GamePlayer.class);
        when(gamePlayer.getId()).thenReturn(uuid);
        when(gamePlayer.getStatus()).thenAnswer(invocationOnMock -> {
            GamePlayerStatus status = mock(GamePlayerStatus.class);
            when(status.isAlive()).thenReturn(alive);
            return status;
        });
        storage.addPlayer(uuid, gamePlayer);
        return gamePlayer;
    }

}