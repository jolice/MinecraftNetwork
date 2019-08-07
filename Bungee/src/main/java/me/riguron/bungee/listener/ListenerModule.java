package me.riguron.bungee.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.bungee.data.GlobalOnline;
import me.riguron.bungee.listener.stream.PlayerServerListener;
import me.riguron.bungee.listener.stream.ServerStreamListener;
import me.riguron.common.shutdown.ShutdownHooks;
import me.riguron.server.ServerName;
import me.riguron.server.repository.GlobalOnlineRepository;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.server.repository.PlayerRepository;
import me.riguron.server.repository.ServerRepository;
import me.riguron.system.listener.ListenerPostProcessor;
import me.riguron.system.listener.ListenerRegistration;
import me.riguron.system.message.PlayerMessaging;
import me.riguron.system.party.PartyService;
import me.riguron.system.task.TaskFactory;
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
