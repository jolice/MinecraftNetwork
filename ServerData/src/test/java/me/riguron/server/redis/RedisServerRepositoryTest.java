package me.riguron.server.redis;

import me.riguron.server.mapper.StandardServerMapper;
import me.riguron.server.repository.ServerFieldType;
import me.riguron.server.type.Server;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RedisServerRepositoryTest {

    private RedisServerRepository repository = new RedisServerRepository(new RedisTransactions(new JedisPool()));


    @Test
    public void addServer() {
        Server server = addServer("name");
        Server inRedis = repository.get("name", new StandardServerMapper());
        assertEquals(server, inRedis);

    }

    @Test
    public void set() {
        addServer("new");
        repository.set("new", ServerFieldType.ENABLED, "false");
        Server server = repository.get("new", new StandardServerMapper());
        assertFalse(server.isEnabled());
    }

    @Test
    public void incrementOnline() {
        Server server = addServer("sample");
        for (int i = 0; i < 10; i++) {
            repository.incrementOnline(server.getName());
        }
        assertEquals(10, repository.get("sample", new StandardServerMapper()).getOnlinePlayers());
    }

    @Test
    public void decrementOnline() {
        Server server = addServer("sample");
        for (int i = 0; i < 10; i++) {
            repository.incrementOnline(server.getName());
        }
        for (int i = 0; i < 5; i++) {
            repository.decrementOnline(server.getName());
        }
        assertEquals(5, repository.get("sample", new StandardServerMapper()).getOnlinePlayers());
    }



    private Server addServer(String name) {
        Server server = new Server(name, true, 0, 10);
        repository.addServer(server,
                new StandardServerMapper());
        return server;
    }


}