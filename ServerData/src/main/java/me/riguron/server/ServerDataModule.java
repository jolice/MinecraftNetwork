package me.riguron.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.server.redis.RedisModule;
import me.riguron.server.repository.RepositoryModule;

public class ServerDataModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RedisModule());
        install(new RepositoryModule());
    }

    @Singleton
    @Provides
    public ServerGroup group(ServerName serverName) {
        return new ConvertingServerGroup(serverName);
    }

    @Provides
    @Singleton
    public ServerName serverName() {
        return ServerName.INSTANCE;
    }

    @Provides
    @Singleton
    public ServerProfile serverProfile(ServerGroup serverGroup, ServerName serverName) {
        return new ServerProfile(serverGroup, serverName);
    }
}
