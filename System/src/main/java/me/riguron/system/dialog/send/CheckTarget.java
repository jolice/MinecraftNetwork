package me.riguron.system.dialog.send;

import lombok.RequiredArgsConstructor;
import me.riguron.system.dialog.MessageSendResponse;
import me.riguron.system.dialog.PrivateMessage;
import me.riguron.system.dialog.ignore.IgnoreRepository;
import me.riguron.system.player.PlayerProfile;

/**
 * Fourth stage of message sending performing several checks of
 * message receiver: whether the target is ignoring sender or has
 * disabled private messages. If all checks are passed, algorithms
 * proceeds to the final stage.
 */
@RequiredArgsConstructor
public class CheckTarget {

    private final IgnoreRepository ignoreRepository;
    private final FinalStage finalStage;

    public MessageSendResponse checkTargetAndSend(PlayerProfile targetProfile, PrivateMessage privateMessage, String targetProxy) {
        if (ignoreRepository.isIgnoring(targetProfile.getUuid(), privateMessage.getFromName())) {
            return MessageSendResponse.IGNORING;
        } else if (!targetProfile.getPlayerPreferences().isPrivateMessages()) {
            return MessageSendResponse.DISABLED;
        } else {

            return finalStage.sendFinalStage(targetProfile, privateMessage, targetProxy);
        }
    }
}
