package me.riguron.bungee.message;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.bungee.message.party.PartyHandlerModule;
import me.riguron.messaging.handler.MessageHandler;
import net.md_5.bungee.api.ProxyServer;

public class MessageHandlerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PartyHandlerModule());
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> alertHandler(ProxyServer proxyServer) {
        return new AlertHandler(proxyServer);
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> privateMessageHandler(ProxyServer proxyServer) {
        return new PrivateMessageHandler(proxyServer);
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> kickHandler(ProxyServer proxyServer) {
        return new KickHandler(proxyServer);
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> registerServerHandler(ProxyServer proxyServer) {
        return new RegisterServerHandler(proxyServer);
    }



}
