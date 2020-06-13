package com.github.jolice.system.dialog.send;

import com.github.jolice.system.player.PlayerProfileRepository;
import lombok.RequiredArgsConstructor;
import com.github.jolice.messaging.MessagingService;
import com.github.jolice.messaging.message.PrivateMessageCommand;
import com.github.jolice.system.dialog.MessageSendResponse;
import com.github.jolice.system.dialog.PrivateMessage;
import com.github.jolice.system.dialog.PrivateMessageRecord;
import com.github.jolice.system.dialog.PrivateMessageRepository;
import com.github.jolice.system.player.PlayerProfile;

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
