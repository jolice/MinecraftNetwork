package me.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import me.riguron.server.ServerName;
import me.riguron.server.mapper.StandardServerMapper;
import me.riguron.server.repository.ServerRepository;
import me.riguron.server.type.Server;

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
