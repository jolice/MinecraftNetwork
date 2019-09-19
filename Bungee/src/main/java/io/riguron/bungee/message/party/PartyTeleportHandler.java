package io.riguron.bungee.message.party;

import lombok.RequiredArgsConstructor;
import io.riguron.messaging.handler.MessageHandler;
import io.riguron.messaging.message.party.PartyTeleport;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class PartyTeleportHandler implements MessageHandler<PartyTeleport> {

    private final ProxyServer proxyServer;

    @Override
    public void accept(PartyTeleport message) {
        ProxiedPlayer player = proxyServer.getPlayer(message.getPlayer());
        if (player != null) {
            player.connect(proxyServer.getServerInfo(message.getTargetServer()));
        }
    }

    @Override
    public Type getMessageType() {
        return PartyTeleport.class;
    }
}
