package com.github.jolice.system.dialog.send;

import com.github.jolice.system.dialog.MessageSendResponse;
import com.github.jolice.system.exception.PlayerNotFoundException;
import com.github.jolice.system.player.PlayerAssociation;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.player.specification.PlayerNameSpecification;
import lombok.RequiredArgsConstructor;
import com.github.jolice.system.dialog.PrivateMessage;

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
