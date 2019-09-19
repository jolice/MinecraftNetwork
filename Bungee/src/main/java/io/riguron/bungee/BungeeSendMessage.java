package io.riguron.bungee;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@RequiredArgsConstructor
public class BungeeSendMessage {

    private final ProxyServer proxyServer;

    public void sendMessage(String name, String message) {
        final ProxiedPlayer player = proxyServer.getPlayer(name);
        if (player != null) {
            player.sendMessage(new TextComponent(message));
        }
    }
}
