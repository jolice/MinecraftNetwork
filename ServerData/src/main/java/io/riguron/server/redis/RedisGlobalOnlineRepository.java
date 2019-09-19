package io.riguron.server.redis;

import io.riguron.server.ServerGroup;
import io.riguron.server.repository.GlobalOnlineRepository;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class RedisGlobalOnlineRepository implements GlobalOnlineRepository {

    private static final String HASH_TABLE = "groups_online";

    private final RedisTransactions redisTransactions;

    @Override
    public Map<String, Integer> getGroupsOnline() {
        return redisTransactions.runTransaction(jedis -> {
            Map<String, Integer> result = new HashMap<>();
            Map<String, String> stringMap = jedis.hgetAll(HASH_TABLE);
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                result.put(entry.getKey(), Integer.valueOf(entry.getValue()));
            }
            return result;
        });
    }

    @Override
    public void increment(ServerGroup group) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hincrBy(HASH_TABLE, group.getName(), 1);
        });
    }

    @Override
    public int getGlobalOnline() {
        return redisTransactions.runTransaction(jedis -> {
            return jedis.hgetAll(HASH_TABLE)
                    .values()
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .sum();
        });
    }

    @Override
    public void decrement(ServerGroup group) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hincrBy(HASH_TABLE, group.getName(), -1);
        });
    }
}
