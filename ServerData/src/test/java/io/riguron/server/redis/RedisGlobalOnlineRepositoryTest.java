package io.riguron.server.redis;

import io.riguron.server.ServerGroup;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RedisGlobalOnlineRepositoryTest {

    private RedisGlobalOnlineRepository repository = new RedisGlobalOnlineRepository(new RedisTransactions(new JedisPool()));

    @Test
    public void getGroupsOnline() {
        ServerGroup bw = serverGroup("bw");
        ServerGroup sw = serverGroup("sw");
        ServerGroup lobby = serverGroup("lobby");

        increment(bw, 5);
        increment(sw, 4);
        increment(lobby, 6);

        Map<String, Integer> groupsOnline = repository.getGroupsOnline();
        assertEquals(3, groupsOnline.size());
        assertEquals(5, groupsOnline.get("bw"));
        assertEquals(4, groupsOnline.get("sw"));
        assertEquals(6, groupsOnline.get("lobby"));
        assertEquals(15, repository.getGlobalOnline());
    }


    private void increment(ServerGroup group, int amount) {
        for (int i = 0; i < amount; i++) {
            repository.increment(group);
        }
    }

    @Test
    public void decrement() {
        ServerGroup group = serverGroup("group");
        increment(group, 5);
        for (int i = 0; i < 2; i++) {
            repository.decrement(group);
        }
        Map<String, Integer> groupsOnline = repository.getGroupsOnline();
        assertEquals(3, groupsOnline.get("group"));
    }


    private ServerGroup serverGroup(String name) {
        return () -> name;
    }
}