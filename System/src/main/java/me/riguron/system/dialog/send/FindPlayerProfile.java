package me.riguron.system.dialog.send;

import lombok.RequiredArgsConstructor;
import me.riguron.system.dialog.MessageSendResponse;
import me.riguron.system.dialog.PrivateMessage;
import me.riguron.system.exception.PlayerNotFoundException;
import me.riguron.system.player.PlayerAssociation;
import me.riguron.system.player.PlayerProfileRepository;
import me.riguron.system.player.specification.PlayerNameSpecification;

/**
 * The third stage of message sending. Here, the player's profile is fetched
 * from the database and delegated to the next stage, where it's is evaluated
 * for deciding whether the message can be sent.
 */
@RequiredArgsConstructor
public class FindPlayerProfile {

    private final PlayerProfileRepository playerProfileRepository;
    private final CheckTarget checkTarget;

    public MessageSendResponse findProfileAndProceed(PrivateMessage message, String targetProxy) {
        return playerProfileRepository
                .find(new PlayerNameSpecification(message.getToName()), PlayerAssociation.PREFERENCES)
                .map(targetProfile -> checkTarget.checkTargetAndSend(targetProfile, message, targetProxy))
                .orElseThrow(PlayerNotFoundException::new);

    }
}
