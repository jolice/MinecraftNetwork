package me.riguron.bungee.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import me.riguron.messaging.handler.MessageHandler;
import me.riguron.messaging.message.RegisterServer;

import java.lang.reflect.Type;
import java.net.InetSocketAddress;

@Slf4j
@RequiredArgsConstructor
public class RegisterServerHandler implements MessageHandler<RegisterServer> {

    private final ProxyServer proxyServer;

    @Override
    public void accept(RegisterServer data) {
        ServerInfo info = proxyServer.constructServerInfo(data.getName(), new InetSocketAddress(data.getAddress(), data.getPort()),
                "", false);
        proxyServer.getServers().put(data.getName(), info);
        log.info("Adding server {} to proxy", data.getName());
    }

    @Override
    public Type getMessageType() {
        return RegisterServer.class;
    }
}
