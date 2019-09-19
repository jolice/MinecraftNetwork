package io.riguron.system.dialog.send;

import io.riguron.system.player.PlayerProfileRepository;
import lombok.RequiredArgsConstructor;
import io.riguron.messaging.MessagingService;
import io.riguron.messaging.message.PrivateMessageCommand;
import io.riguron.system.dialog.MessageSendResponse;
import io.riguron.system.dialog.PrivateMessage;
import io.riguron.system.dialog.PrivateMessageRecord;
import io.riguron.system.dialog.PrivateMessageRepository;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;

@RequiredArgsConstructor
public class FinalStage {

    private final MessagingService messagingService;
    private final PlayerProfileRepository playerProfileRepository;
    private final PrivateMessageRepository privateMessageRepository;

    public MessageSendResponse sendFinalStage(PlayerProfile targetProfile, PrivateMessage privateMessage, String targetProxy) {
        PlayerProfile senderProfile = playerProfileRepository.get(privateMessage.getFrom());
        PrivateMessageRecord privateMessageRecord = new PrivateMessageRecord(senderProfile, targetProfile, privateMessage.getText());
        privateMessageRepository.save(privateMessageRecord);
        messagingService.send(new PrivateMessageCommand(senderProfile.getName(), privateMessage.getText()), targetProxy);
        return MessageSendResponse.OK;
    }

}
