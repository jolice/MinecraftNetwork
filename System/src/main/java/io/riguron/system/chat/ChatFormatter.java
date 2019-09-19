package io.riguron.system.chat;

import io.riguron.system.player.PlayerProfileRepository;
import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;

import java.util.UUID;

/**
 * Class responsible for formatting chat messages, i.e prettifying them.
 */
@RequiredArgsConstructor
public class ChatFormatter {

    private final PlayerProfileRepository playerProfileRepository;

    /**
     * Prettifies the incoming message considering player's rank name.
     *
     * @param uuid    id of the player that initiated the chat message
     * @param message the raw message body
     * @return prettified chat message that is displayed to other players in the main chat.
     */
    public String getChatMessage(UUID uuid, String message) {
        PlayerProfile profile = playerProfileRepository.get(uuid);
        return String.format("[%s] %s: %s", profile.getRank().getPrefix(), profile.getName(), message);
    }
}
