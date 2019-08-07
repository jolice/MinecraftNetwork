package me.riguron.bungee.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import me.riguron.server.ServerType;
import me.riguron.server.repository.OnlineIndexingRepository;

/**
 * Listener responsible for lobby load balancing. Each time the
 * player is connected to the lobby, he is redirected to the most
 * currently available lobby (i.e a lobby with minimal players online).
 */
@Slf4j
@RequiredArgsConstructor
public class LobbyBalancer implements Listener {

    private final OnlineIndexingRepository onlineRepository;
    private final ProxyServer bungeeCord;

    @EventHandler
    public void playerConnect(ServerConnectEvent event) {
        if (ServerType.LOBBY.matches(event.getTarget().getName())) {
            String mostFreeServer = onlineRepository.getMostFreeServer(ServerType.LOBBY);
            log.info("Connecting {} to {}", event.getPlayer().getName(), mostFreeServer);
            event.setTarget(bungeeCord.getServerInfo(mostFreeServer));
        }
    }
}
