package me.riguron.bungee.message.party;

import lombok.RequiredArgsConstructor;
import me.riguron.bungee.BungeeSendMessage;
import me.riguron.messaging.handler.MessageHandler;
import me.riguron.messaging.message.party.PartyChat;

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
