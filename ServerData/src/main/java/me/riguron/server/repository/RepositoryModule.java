package me.riguron.server.repository;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.server.redis.*;

public class RepositoryModule extends AbstractModule {

    @Provides
    @Singleton
    public GlobalOnlineRepository globalOnlineRepository(RedisTransactions redisTransactions) {
        return new RedisGlobalOnlineRepository(redisTransactions);
    }

    @Provides
    @Singleton
    public OnlineIndexingRepository onlineIndexingRepository(RedisTransactions redisTransactions) {
        return new RedisOnlineIndexingRepository(redisTransactions);
    }

    @Provides
    @Singleton
    public PlayerRepository playerRepository(RedisTransactions redisTransactions) {
        return new RedisPlayerRepository(redisTransactions);
    }

    @Provides
    @Singleton
    public ServerRepository serverRepository(RedisTransactions redisTransactions) {
        return new RedisServerRepository(redisTransactions);
    }
}

