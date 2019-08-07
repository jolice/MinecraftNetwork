package me.riguron.game.server;

import lombok.RequiredArgsConstructor;
import me.riguron.common.shutdown.ShutdownHook;
import me.riguron.server.ServerName;
import me.riguron.server.repository.ServerFieldType;
import me.riguron.server.repository.ServerRepository;

@RequiredArgsConstructor
public class GameServerUnregister implements ShutdownHook {

    private final ServerRepository serverRepository;
    private final ServerName serverName;

    @Override
    public void run() {
        serverRepository.set(serverName.get(), ServerFieldType.ACTIVE, false);
    }
}
