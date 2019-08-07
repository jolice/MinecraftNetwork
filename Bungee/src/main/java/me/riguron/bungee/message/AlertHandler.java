package me.riguron.bungee.message;

import lombok.RequiredArgsConstructor;
import me.riguron.messaging.handler.MessageHandler;
import me.riguron.messaging.message.Alert;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class AlertHandler implements MessageHandler<Alert> {

    private final ProxyServer proxyServer;

    @Override
    public void accept(Alert alert) {
        proxyServer.getPlayers().forEach(proxiedPlayer -> proxiedPlayer.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(alert.getMessage())));
    }

    @Override
    public Type getMessageType() {
        return Alert.class;
    }
}
