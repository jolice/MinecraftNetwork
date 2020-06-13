package com.github.jolice.server;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import io.riguron.server.ServerName;
import io.riguron.server.mapper.StandardServerMapper;
import io.riguron.server.repository.ServerRepository;
import io.riguron.server.type.Server;

@RequiredArgsConstructor
public class DefaultServerRegistration implements ServerRegistration {

    private final ServerRepository serverRepository;
    private final ServerName serverName;
    private final Plugin plugin;

    @Override
    public void run() {
        serverRepository.addServer(
                new Server(serverName.get(), true, 0, plugin.getServer().getMaxPlayers()),
                new StandardServerMapper());
    }
}
