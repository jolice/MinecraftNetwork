package io.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Server;
import io.riguron.system.task.startup.PostLoadTask;
import io.riguron.messaging.Message;
import io.riguron.messaging.MessagingChannels;
import io.riguron.messaging.MessagingService;
import io.riguron.messaging.message.RegisterServer;
import io.riguron.server.ServerName;

/**
 * Task responsible for registering server on proxies.
 */
@Slf4j
@RequiredArgsConstructor
public class ServerProxyRegistration implements PostLoadTask {

    private final MessagingService messagingService;
    private final Server server;
    private final ServerName serverName;

    @Override
    public void run() {
        Message serverRegistration = new RegisterServer(serverName.get(), server.getIp(), server.getPort(), server.getMaxPlayers());
        log.info("Sending server registration to proxy ({}) ", serverRegistration);
        messagingService.send(serverRegistration, MessagingChannels.PROXY);
    }
}
