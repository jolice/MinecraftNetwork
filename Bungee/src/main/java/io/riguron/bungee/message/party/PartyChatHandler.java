package io.riguron.bungee.message.party;

import lombok.RequiredArgsConstructor;
import io.riguron.bungee.BungeeSendMessage;
import io.riguron.messaging.handler.MessageHandler;
import io.riguron.messaging.message.party.PartyChat;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class PartyChatHandler implements MessageHandler<PartyChat> {

    private final BungeeSendMessage bungeeSendMessage;

    @Override
    public void accept(PartyChat message) {
        bungeeSendMessage.sendMessage(message.getReceiver(),
                String.format("[PartyChat] %s: %s ", message.getFrom(), message.getText()));
    }

    @Override
    public Type getMessageType() {
        return PartyChat.class;
    }
}
