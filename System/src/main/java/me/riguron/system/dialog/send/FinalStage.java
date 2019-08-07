package me.riguron.system.dialog.send;

import lombok.RequiredArgsConstructor;
import me.riguron.messaging.MessagingService;
import me.riguron.messaging.message.PrivateMessageCommand;
import me.riguron.system.dialog.MessageSendResponse;
import me.riguron.system.dialog.PrivateMessage;
import me.riguron.system.dialog.PrivateMessageRecord;
import me.riguron.system.dialog.PrivateMessageRepository;
import me.riguron.system.player.PlayerProfile;
import me.riguron.system.player.PlayerProfileRepository;

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
