package io.riguron.bungee.listener;

import io.riguron.server.repository.OnlineIndexingRepository;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LobbyBalancerTest {

    @Test
    public void playerConnect() {
        OnlineIndexingRepository repository = mock(OnlineIndexingRepository.class);
        ProxyServer proxyServer = mock(ProxyServer.class);
        LobbyBalancer lobbyBalancer = new LobbyBalancer(repository, proxyServer);

        ServerConnectEvent event = mock(ServerConnectEvent.class);
        when(event.getTarget()).thenAnswer(invocation -> {
            ServerInfo serverInfo = mock(ServerInfo.class);
            when(serverInfo.getName()).thenReturn("lobby-6");
            return serverInfo;
        });

        when(repository.getMostFreeServer(any())).thenReturn("lobby-3");

        when(event.getPlayer()).thenReturn(mock(ProxiedPlayer.class));
        ServerInfo target =mock(ServerInfo.class);
        when(proxyServer.getServerInfo(eq("lobby-3"))).thenReturn(target);

        lobbyBalancer.playerConnect(event);

        verify(event).setTarget(eq(target));



    }
}