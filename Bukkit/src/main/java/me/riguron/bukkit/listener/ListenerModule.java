package me.riguron.bukkit.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.bukkit.listener.stream.DisconnectListener;
import me.riguron.bukkit.listener.stream.JoinBroadcastingListener;
import me.riguron.bukkit.listener.stream.LoginListener;
import me.riguron.bukkit.punish.PunishChatListener;
import me.riguron.bukkit.server.ServerUnregister;
import me.riguron.bukkit.stream.StreamBroadcast;
import me.riguron.command.engine.CommandFrontController;
import me.riguron.common.shutdown.ShutdownHooks;
import me.riguron.system.chat.ChatCooldowns;
import me.riguron.system.chat.ChatFormatter;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.listener.ListenerPostProcessor;
import me.riguron.system.listener.ListenerRegistration;
import me.riguron.system.login.chain.LoginChainExecutor;
import me.riguron.system.player.PlayerLeaveHandler;
import me.riguron.system.punishment.PunishmentChecker;
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
