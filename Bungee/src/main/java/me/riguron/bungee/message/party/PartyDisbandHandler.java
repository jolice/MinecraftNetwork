package me.riguron.bungee.message.party;

import lombok.RequiredArgsConstructor;
import me.riguron.bungee.BungeeSendMessage;
import me.riguron.messaging.handler.MessageHandler;
import me.riguron.messaging.message.party.PartyDisband;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class PartyDisbandHandler implements MessageHandler<PartyDisband> {

    private final BungeeSendMessage bungeeSendMessage;

    @Override
    public void accept(PartyDisband message) {
        bungeeSendMessage.sendMessage(message.getMemberName(), "Your party has been disbanded!");
    }

    @Override
    public Type getMessageType() {
        return PartyDisband.class;
    }
}
