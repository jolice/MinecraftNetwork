package me.riguron.bungee.message;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import me.riguron.messaging.handler.MessageHandler;
import me.riguron.messaging.message.PrivateMessageCommand;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class PrivateMessageHandler implements MessageHandler<PrivateMessageCommand> {

    private final ProxyServer proxyServer;

    @Override
    public void accept(PrivateMessageCommand message) {
        proxyServer.getPlayer(message.getFrom()).sendMessage(new TextComponent(message.getText()));
    }

    @Override
    public Type getMessageType() {
        return PrivateMessageCommand.class;
    }
}
