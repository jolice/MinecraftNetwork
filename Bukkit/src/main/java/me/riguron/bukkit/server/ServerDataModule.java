package me.riguron.bukkit.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.server.ServerProfile;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.system.task.TaskFactory;
import org.bukkit.plugin.Plugin;
import me.riguron.system.task.startup.PostLoadTask;
import me.riguron.messaging.MessagingService;
import me.riguron.server.ServerName;
import me.riguron.server.repository.ServerRepository;
import me.riguron.server.type.Server;

public class ServerDataModule extends AbstractModule {

    @Singleton
    @Provides
    public Server server(ServerName serverName, Plugin plugin) {
        return new Server(serverName.get(), true, 0, plugin.getServer().getMaxPlayers());
    }

    @Singleton
    @ProvidesIntoOptional(value = ProvidesIntoOptional.Type.DEFAULT)
    public ServerRegistration defaultRegistration(ServerRepository repository, ServerName serverName, Plugin plugin) {
        return new DefaultServerRegistration(repository, serverName, plugin);
    }

    @ProvidesIntoSet
    @Singleton
    public PostLoadTask serverRegistration(ServerRegistration serverRegistration) {
        return serverRegistration;
    }

    @ProvidesIntoSet
    @Singleton
    public PostLoadTask serverProxyRegistration(MessagingService messagingService, Plugin plugin, ServerName serverName) {
        return new ServerProxyRegistration(messagingService, plugin.getServer(), serverName);
    }

    @Provides
    @Singleton
    public ServerUnregister serverUnregister(ServerRepository serverRepository, ServerName serverName) {
        return new ServerUnregister(serverRepository, serverName);
    }

    @ProvidesIntoSet
    @Singleton
    public ServerIndexingRegistration serverIndexing(OnlineIndexingRepository repository, ServerProfile serverProfile) {
        return new ServerIndexingRegistration(repository, serverProfile);
    }

    @Provides
    @Singleton
    public OnlineIndexer onlineIndexer(ServerProfile serverProfile, OnlineIndexingRepository repository, TaskFactory taskFactory) {
        return new OnlineIndexer(serverProfile, taskFactory, repository);
    }

}
