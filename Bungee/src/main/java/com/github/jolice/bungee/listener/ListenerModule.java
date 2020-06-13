package io.riguron.bungee.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.bungee.listener.stream.PlayerServerListener;
import io.riguron.bungee.data.GlobalOnline;
import io.riguron.bungee.listener.stream.ServerStreamListener;
import io.riguron.common.shutdown.ShutdownHooks;
import io.riguron.server.ServerName;
import io.riguron.server.repository.GlobalOnlineRepository;
import io.riguron.server.repository.OnlineIndexingRepository;
import io.riguron.server.repository.PlayerRepository;
import io.riguron.server.repository.ServerRepository;
import io.riguron.system.listener.ListenerPostProcessor;
import io.riguron.system.listener.ListenerRegistration;
import io.riguron.system.message.PlayerMessaging;
import io.riguron.system.party.PartyService;
import io.riguron.system.task.TaskFactory;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<ListenerPostProcessor<Listener>>() {
        }).asEagerSingleton();
    }

    @ProvidesIntoSet
    @Singleton
    public ListenerRegistration<Listener> listenerRegistration(Plugin plugin) {
        return new BungeeListenerRegistration(plugin);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener lobbyBalancer(OnlineIndexingRepository onlineIndexingRepository, ProxyServer proxyServer) {
        return new LobbyBalancer(onlineIndexingRepository, proxyServer);
    }


    @ProvidesIntoSet
    @Singleton
    public Listener pingListener(GlobalOnline globalOnline) {
        return new PingListener(globalOnline);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener pluginDisableListener(ShutdownHooks shutdownHooks) {
        return new PluginDisableListener(shutdownHooks);
    }



    @ProvidesIntoSet
    @Singleton
    public Listener partyListener(PartyService partyService, PlayerMessaging playerMessaging, TaskFactory taskFactory) {
        return new PartyListener(partyService, playerMessaging, taskFactory);
    }

    @Singleton
    @ProvidesIntoSet
    public Listener playerServerListener(ServerName serverName, TaskFactory taskFactory, PlayerRepository repository) {
        return new PlayerServerListener(serverName, repository, taskFactory);
    }

    @Singleton
    @ProvidesIntoSet
    public ServerStreamListener streamListener(GlobalOnlineRepository globalOnlineRepository, ServerRepository repository, TaskFactory taskFactory) {
        return new ServerStreamListener(globalOnlineRepository, repository, taskFactory);
    }
}
