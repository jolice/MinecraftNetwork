package me.riguron.command.sender;

import lombok.RequiredArgsConstructor;
import me.riguron.command.base.SenderKind;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.player.PlayerProfileRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class DefaultSenderFactory implements SenderFactory {

    private final PlayerProfileRepository playerProfileRepository;
    private final SendMessage consoleSendMessage;
    private final SendMessage playerSendMessage;

    @Override
    public Sender newSender(UUID uuid, SenderKind senderKind) {
        switch (senderKind) {
            case PLAYER:
                return new PlayerSender(this.playerProfileRepository.get(uuid), playerSendMessage);
            case CONSOLE:
                return new ConsoleSender(consoleSendMessage);
            default:
                throw new IllegalArgumentException(senderKind.name());
        }
    }
}
