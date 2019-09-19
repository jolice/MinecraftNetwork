package io.riguron.bukkit.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.bukkit.punish.PunishChatListener;
import io.riguron.bukkit.listener.stream.DisconnectListener;
import io.riguron.bukkit.listener.stream.JoinBroadcastingListener;
import io.riguron.bukkit.listener.stream.LoginListener;
import io.riguron.bukkit.punish.PunishChatListener;
import io.riguron.bukkit.server.ServerUnregister;
import io.riguron.bukkit.stream.StreamBroadcast;
import io.riguron.command.engine.CommandFrontController;
import io.riguron.common.shutdown.ShutdownHooks;
import io.riguron.system.chat.ChatCooldowns;
import io.riguron.system.chat.ChatFormatter;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.listener.ListenerPostProcessor;
import io.riguron.system.listener.ListenerRegistration;
import io.riguron.system.login.chain.LoginChainExecutor;
import io.riguron.system.player.PlayerLeaveHandler;
import io.riguron.system.punishment.PunishmentChecker;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<ListenerPostProcessor<Listener>>() {
        }).asEagerSingleton();
    }

    @Singleton
    @ProvidesIntoSet
    public Listener joinListener(StreamBroadcast streamBroadcast) {
        return new JoinBroadcastingListener(streamBroadcast);
    }

    @Singleton
    @ProvidesIntoSet
    public Listener loginListener(LoginChainExecutor loginChainExecutor) {
        return new LoginListener(loginChainExecutor);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener playerChatListener(ChatFormatter chatFormatter, SendMessage sendMessage, ChatCooldowns chatCooldowns) {
        return new PlayerChatListener(chatFormatter, chatCooldowns, sendMessage);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener punishChatListener(PunishmentChecker punishmentChecker) {
        return new PunishChatListener(punishmentChecker);
    }

    @Singleton
    @ProvidesIntoSet
    public Listener serverDisableListener(Plugin plugin, ShutdownHooks shutdownHooks, ServerUnregister serverUnregister) {
        return new ServerDisableListener(plugin, shutdownHooks, serverUnregister);
    }

    @Singleton
    @Provides
    public RegisterListener registerListener(Plugin plugin) {
        return new RegisterListener(plugin);
    }

    @Singleton
    @Provides
    public ListenerRegistration<Listener> listenerRegistration(RegisterListener registerListener) {
        return new BukkitListenerRegistration(registerListener);
    }

    @Singleton
    @ProvidesIntoSet
    public Listener commandListener(CommandFrontController commandFrontController) {
        return new BukkitCommandListener(commandFrontController);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener disconnectListener(PlayerLeaveHandler playerProfile) {
        return new DisconnectListener(playerProfile);
    }

}
