package io.riguron.system.punishment.type;

import lombok.RequiredArgsConstructor;
import io.riguron.messaging.MessagingService;
import io.riguron.messaging.message.KickProxyCommand;
import io.riguron.server.repository.PlayerRepository;

@RequiredArgsConstructor
public class ProxyKick {

    private final PlayerRepository playerRepository;
    private final MessagingService messagingService;

    /**
     * Sends a kick packet to the proxy the punished player is currently playing on.
     *
     * @param target punished player
     * @param reason reason of the kick, will be displayed to the player on the disconnect screen
     * @return whether the punished player was kicked (depends on whether he was online at any proxy)
     */
    public boolean kick(String target, String reason) {
        return playerRepository.getPlayerProxy(target)
                .map(proxyName -> {
                    messagingService.send(new KickProxyCommand(target, reason), proxyName);
                    return Boolean.TRUE;
                }).orElse(Boolean.FALSE);
    }
}
