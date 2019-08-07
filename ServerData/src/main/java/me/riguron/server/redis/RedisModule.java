package me.riguron.server.redis;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import redis.clients.jedis.JedisPool;

public class RedisModule extends AbstractModule {

    @Provides
    @Singleton
    public RedisTransactions redisTransactions(JedisPool jedisPool) {
        return new RedisTransactions(jedisPool);
    }

    @Provides
    @Singleton
    public JedisPool jedisPool() {
        return new JedisPool();
    }
}
