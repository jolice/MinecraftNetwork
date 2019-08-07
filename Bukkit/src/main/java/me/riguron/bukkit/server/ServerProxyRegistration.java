package me.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Server;
import me.riguron.system.task.startup.PostLoadTask;
import me.riguron.messaging.Message;
import me.riguron.messaging.MessagingChannels;
import me.riguron.messaging.MessagingService;
import me.riguron.messaging.message.RegisterServer;
import me.riguron.server.ServerName;

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
