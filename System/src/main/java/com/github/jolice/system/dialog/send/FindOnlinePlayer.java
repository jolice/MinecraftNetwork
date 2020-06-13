package com.github.jolice.system.dialog.send;

import lombok.RequiredArgsConstructor;
import com.github.jolice.server.repository.PlayerRepository;
import com.github.jolice.system.dialog.MessageSendResponse;
import com.github.jolice.system.dialog.PrivateMessage;

/**
 * The second stage of message sending process. It's checked
 * whether the player is online on some of proxies, and if he is,
 * sending is delegated to the next stage.
 */
@RequiredArgsConstructor
public class FindOnlinePlayer {

    private final PlayerRepository playerRepository;
    private final FindPlayerProfile findPlayerProfile;

    public MessageSendResponse findAndSend(PrivateMessage message) {
        return playerRepository
                .getPlayerProxy(message.getToName())
                .map(x -> findPlayerProfile.findProfileAndProceed(message, x))
                .orElse(MessageSendResponse.OFFLINE);

    }
}
