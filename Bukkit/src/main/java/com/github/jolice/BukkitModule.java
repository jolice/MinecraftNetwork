package com.github.jolice;

import com.github.jolice.command.CommandListModule;
import com.github.jolice.config.WorldDeserializer;
import com.github.jolice.config.WorldSerializer;
import com.github.jolice.gui.GUIFactory;
import com.github.jolice.item.ItemModule;
import com.github.jolice.listener.ListenerModule;
import com.github.jolice.listener.RegisterListener;
import com.github.jolice.message.BukkitBroadcast;
import com.github.jolice.message.ConsoleSendMessage;
import com.github.jolice.message.PlayerSendMessage;
import com.github.jolice.selector.SelectorModule;
import com.github.jolice.server.PlayerServerConnector;
import com.github.jolice.server.ServerDataModule;
import com.github.jolice.shop.ShopModule;
import com.github.jolice.stream.DefaultStreamMessageProvider;
import com.github.jolice.stream.StreamBroadcast;
import com.github.jolice.stream.StreamMessageProvider;
import com.github.jolice.task.BukkitTaskModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.name.Named;
import io.riguron.system.dialog.PrivateMessageModule;
import io.riguron.system.internalization.InternalizationService;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.punishment.PunishmentModule;
import io.riguron.system.stream.Broadcast;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class BukkitModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ListenerModule());
        install(new SelectorModule());
        install(new ShopModule());
        install(new BukkitTaskModule());
        install(new PunishmentModule());
        install(new PrivateMessageModule());
        install(new ServerDataModule());
        install(new ItemModule());
        install(new CommandListModule());
    }

    @Provides
    @Singleton
    public StreamBroadcast streamMessages(SendMessage sendMessage, StreamMessageProvider streamMessageProvider) {
        return new StreamBroadcast(streamMessageProvider, sendMessage);
    }

    @ProvidesIntoOptional(ProvidesIntoOptional.Type.DEFAULT)
    @Singleton
    public StreamMessageProvider streamMessageProvider(PlayerProfileRepository playerProfileRepository) {
        return new DefaultStreamMessageProvider(playerProfileRepository);
    }

    @Provides
    @Singleton
    public Broadcast broadcast(Server server, SendMessage sendMessage) {
        return new BukkitBroadcast(server, sendMessage);
    }

    @Provides
    @Singleton
    @Named("PlayerSendMessage")
    public SendMessage playerSendMessage(PlayerProfileRepository playerService, InternalizationService internalizationService, Server server) {
        return new PlayerSendMessage(playerService, internalizationService, server);
    }

    @Provides
    @Singleton
    @Named("ConsoleSendMessage")
    public SendMessage consoleSendMessage(Server server, InternalizationService internalizationService) {
        return new ConsoleSendMessage(server, internalizationService);
    }

    @Provides
    @Singleton
    public SendMessage defaultSendMessage(@Named("PlayerSendMessage") SendMessage sendMessage) {
        return sendMessage;
    }

    @Provides
    @Singleton
    public Gson gson(Server server) {
        return new GsonBuilder()
                .registerTypeAdapter(World.class, new WorldDeserializer(server))
                .registerTypeAdapter(World.class, new WorldSerializer())
                .setPrettyPrinting()
                .create();
    }

    @Provides
    @Singleton
    public GUIFactory guiFactory(Server server, RegisterListener registerListener, Plugin plugin) {
        return new GUIFactory(server, registerListener, plugin);
    }

    @Provides
    @Singleton
    public BukkitScheduler bukkitScheduler(Server server) {
        return server.getScheduler();
    }


    @Provides
    @Singleton
    public PlayerServerConnector playerServerConnector(Server server, Plugin plugin) {
        return new PlayerServerConnector(server, plugin);
    }

}
