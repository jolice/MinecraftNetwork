package io.riguron.bungee.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import io.riguron.bungee.data.GlobalOnline;
import io.riguron.bungee.data.GlobalOnlineUpdater;
import io.riguron.bungee.listener.ListenerModule;
import io.riguron.bungee.task.BungeeTaskFactory;
import io.riguron.bungee.ProxyChannelDeclaration;
import io.riguron.bungee.message.BungeeMessagingModule;
import io.riguron.messaging.MessageListener;
import io.riguron.server.ServerName;
import io.riguron.server.repository.GlobalOnlineRepository;
import io.riguron.system.task.TaskFactory;
import io.riguron.system.task.startup.PostLoadTask;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;

public class BungeeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ProxyChannelDeclaration.class).asEagerSingleton();
        install(new ListenerModule());
        install(new BungeeMessagingModule());
    }

    @Singleton
    @Provides
    public PostLoadTask channelInitializer(MessageListener messageListener, @Named("Read") Channel channel, ServerName serverName) {
        return new ProxyChannelDeclaration(messageListener, serverName, channel);
    }

    @Provides
    @Singleton
    public TaskFactory taskFactory(TaskScheduler taskScheduler, Plugin plugin) {
        return new BungeeTaskFactory(taskScheduler, plugin);
    }

    @Provides
    @Singleton
    public GlobalOnline globalOnline(GlobalOnlineRepository globalOnlineRepository) {
        return new GlobalOnline(globalOnlineRepository);
    }

    @ProvidesIntoSet
    @Singleton
    public GlobalOnlineUpdater updater(GlobalOnline globalOnline) {
        return new GlobalOnlineUpdater(globalOnline);
    }

    @Provides
    @Singleton
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }


}
