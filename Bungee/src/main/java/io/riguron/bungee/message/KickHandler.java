package io.riguron.bungee.message;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import io.riguron.messaging.handler.MessageHandler;
import io.riguron.messaging.message.KickProxyCommand;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class KickHandler implements MessageHandler<KickProxyCommand> {

    private final ProxyServer proxyServer;

    @Override
    public void accept(KickProxyCommand message) {
        ProxiedPlayer player = proxyServer.getPlayer(message.getPlayerName());
        if (player != null) {
            player.disconnect(new TextComponent(message.getReason()));
        }
    }

    @Override
    public Type getMessageType() {
        return KickProxyCommand.class;
    }
}
