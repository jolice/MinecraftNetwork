package io.riguron.bungee.message.party;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.bungee.BungeeSendMessage;
import io.riguron.messaging.handler.MessageHandler;
import net.md_5.bungee.api.ProxyServer;

public class PartyHandlerModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> partyInviteHandler(BungeeSendMessage bungeeSendMessage) {
        return new PartyInviteHandler(bungeeSendMessage);
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> partyChatHandler(BungeeSendMessage bungeeSendMessage) {
        return new PartyChatHandler(bungeeSendMessage);
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> partyTeleportHandler(ProxyServer proxyServer) {
        return new PartyTeleportHandler(proxyServer);
    }

    @ProvidesIntoSet
    @Singleton
    public MessageHandler<?> partyDisbandHandler(BungeeSendMessage bungeeSendMessage) {
        return new PartyDisbandHandler(bungeeSendMessage);
    }
}
