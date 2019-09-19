package io.riguron.server.redis;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public class RedisTransactions {

    private final JedisPool jedisPool;

    public <T> T runTransaction(Function<Jedis, T> action) {
        try (Jedis jedis = jedisPool.getResource()) {
            return action.apply(jedis);
        }
    }

    public void runTransaction(Consumer<Jedis> action) {
        try (Jedis jedis = jedisPool.getResource()) {
            action.accept(jedis);
        }
    }
}
