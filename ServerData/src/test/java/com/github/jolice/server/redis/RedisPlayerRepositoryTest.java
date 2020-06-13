package com.github.jolice.server.redis;

import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class RedisPlayerRepositoryTest {
    
    private static final String PLAYER_NAME = "riguron";

    private RedisPlayerRepository repository = new RedisPlayerRepository(new RedisTransactions(new JedisPool()));

    @Test
    public void setPlayerServer() {
        repository.setPlayerServer("bw-1", PLAYER_NAME);
        Optional<String> result = repository.getPlayerServer(PLAYER_NAME);
        assertTrue(result.isPresent());
        assertEquals("bw-1", result.get());
    }

    @Test
    public void getPlayersProxies() {
        repository.setPlayerProxy("proxy-1", "a");
        repository.setPlayerProxy("proxy-2", "b");
        repository.setPlayerProxy("proxy-3", "c");
        Map<String, String> playersProxies = repository.getPlayersProxies(Arrays.asList("a", "b", "c"));
        assertEquals(new HashSet<>(Arrays.asList("proxy-1", "proxy-2", "proxy-3")), new HashSet<>(playersProxies.values()));
    }

    @Test
    public void setPlayerProxy() {
        repository.setPlayerProxy("proxy-1", PLAYER_NAME);
         Optional<String> result = repository.getPlayerProxy(PLAYER_NAME);
        assertTrue(result.isPresent());
        assertEquals("proxy-1", result.get());
    }
    

    @Test
    public void deletePlayerData() {
        repository.setPlayerProxy("proxy-1", PLAYER_NAME);
        repository.setPlayerServer("bw-1", PLAYER_NAME);
        repository.deletePlayerData(PLAYER_NAME);
        assertFalse(repository.getPlayerProxy(PLAYER_NAME).isPresent());
        assertFalse(repository.getPlayerServer(PLAYER_NAME).isPresent());
    }



}