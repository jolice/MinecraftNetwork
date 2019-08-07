package me.riguron.bukkit.stream;

import lombok.RequiredArgsConstructor;
import me.riguron.system.internalization.Message;
import me.riguron.system.player.PlayerProfileRepository;

import java.util.UUID;

/**
 * Default implementation providing plain messages.
 */
@RequiredArgsConstructor
public class DefaultStreamMessageProvider implements StreamMessageProvider {

    private final PlayerProfileRepository playerProfileRepository;

    @Override
    public Message getStreamMessage(StreamType streamType, UUID joinedPlayer) {
        return new Message(String.format("stream.message.%s", streamType.getDescription()), playerProfileRepository.get(joinedPlayer).getName());
    }
}
