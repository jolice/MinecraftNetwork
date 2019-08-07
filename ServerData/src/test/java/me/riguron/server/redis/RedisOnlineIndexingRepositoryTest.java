package me.riguron.server.redis;

import me.riguron.server.ServerGroup;
import me.riguron.server.repository.FreeServersQueryOptions;
import me.riguron.server.repository.OnlineIndexingRepository;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RedisOnlineIndexingRepositoryTest {

    private OnlineIndexingRepository repository = new RedisOnlineIndexingRepository(new RedisTransactions(new JedisPool()));



    @Test
    public void getMostFreeServer() {
        ServerGroup bw = group("bw");
        repository.addServer(bw, "bw-1");
        repository.addServer(bw, "bw-2");
        repository.addServer(bw, "bw-3");
        repository.addServer(bw, "bw-4");
        repository.addServer(bw, "bw-12");

        incrementOnline(bw, "bw-1", 5);
        incrementOnline(bw, "bw-2", 3);
        incrementOnline(bw, "bw-3", 9);
        incrementOnline(bw, "bw-4", 2);
        incrementOnline(bw, "bw-12", 22);

        assertEquals("bw-4", repository.getMostFreeServer(bw));

        List<String> mostFreeServers = repository.getMostFreeServers(bw, new FreeServersQueryOptions(6, 10));
        assertEquals(new HashSet<>(Arrays.asList("bw-4", "bw-2", "bw-1")), new HashSet<>(mostFreeServers));

    }

    private void incrementOnline(ServerGroup group, String server, int amount) {
        for (int i = 0; i < amount; i++) {
            repository.incrementOnline(group, server);
        }
    }


    @Test
    public void removeServerData() {
        ServerGroup sw = group("sw");
        repository.addServer(sw, "sw-12");
        repository.addServer(sw, "sw-14");
        repository.incrementOnline(sw, "sw-14");
        assertEquals("sw-12", repository.getMostFreeServer(sw));
        repository.removeServerData(sw, "sw-12");
        assertEquals("sw-14", repository.getMostFreeServer(sw));

    }



    private ServerGroup group(String name) {
        return () -> name;
    }

}