package com.github.jolice.system.message;

import lombok.RequiredArgsConstructor;
import com.github.jolice.messaging.Message;
import com.github.jolice.messaging.MessagingService;
import com.github.jolice.server.repository.PlayerRepository;

import java.util.Collection;
import java.util.function.Function;

/**
 * Class containing convenience methods for messaging.
 */
@RequiredArgsConstructor
public class PlayerMessaging {

    private final MessagingService messagingService;
    private final PlayerRepository playerRepository;

    /**
     * Finds player's proxy and sends a specified message to it.
     *
     * @param message    a message
     * @param playerName name of the player
     * @return whether the message was sent. It depends on whether the target player was online.
     */
    public boolean sendTo(Message message, String playerName) {
        return playerRepository.
                getPlayerProxy(playerName)
                .map(playerProxy -> {
                    messagingService.send(message, playerProxy);
                    return true;
                }).orElse(Boolean.FALSE);
    }

    /**
     * Finds proxies of multiple players and sends a message to each of them.
     *
     * @param playerNames message receivers
     * @param mapper      mapping function that produces a message from player's name.
     */
    public void distribute(Collection<String> playerNames, Function<String, Message> mapper) {
        if (!playerNames.isEmpty()) {
            playerRepository.getPlayersProxies(playerNames).forEach((playerName, proxyName) -> {
                messagingService.send(mapper.apply(playerName), proxyName);
            });
        }
    }

    public boolean isOnline(String playerName) {
        return playerRepository.getPlayerProxy(playerName).isPresent();
    }
}
