package io.riguron.bungee.message.party;

import lombok.RequiredArgsConstructor;
import io.riguron.bungee.BungeeSendMessage;
import io.riguron.messaging.handler.MessageHandler;
import io.riguron.messaging.message.party.PartyInvite;
import io.riguron.system.party.PartyService;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class PartyInviteHandler implements MessageHandler<PartyInvite> {

    private final BungeeSendMessage bungeeSendMessage;

    @Override
    public void accept(PartyInvite message) {
        bungeeSendMessage.sendMessage(message.getInvited(),
                String.format("You was invited to party by %s. You have %d to accept the invitation", message.getOwner(), PartyService.EXPIRATION_TIME)
        );
    }

    @Override
    public Type getMessageType() {
        return PartyInvite.class;
    }
}
