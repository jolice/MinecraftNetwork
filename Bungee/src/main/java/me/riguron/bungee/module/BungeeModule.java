package me.riguron.bungee.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import me.riguron.bungee.ProxyChannelDeclaration;
import me.riguron.bungee.data.GlobalOnline;
import me.riguron.bungee.data.GlobalOnlineUpdater;
import me.riguron.bungee.listener.ListenerModule;
import me.riguron.bungee.message.BungeeMessagingModule;
import me.riguron.bungee.task.BungeeTaskFactory;
import me.riguron.messaging.MessageListener;
import me.riguron.server.ServerName;
import me.riguron.server.repository.GlobalOnlineRepository;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.startup.PostLoadTask;
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
