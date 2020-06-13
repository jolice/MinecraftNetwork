package com.github.jolice.game.server;

import lombok.RequiredArgsConstructor;
import com.github.jolice.common.shutdown.ShutdownHook;
import io.riguron.server.ServerName;
import io.riguron.server.repository.ServerFieldType;
import io.riguron.server.repository.ServerRepository;

@RequiredArgsConstructor
public class GameServerUnregister implements ShutdownHook {

    private final ServerRepository serverRepository;
    private final ServerName serverName;

    @Override
    public void run() {
        serverRepository.set(serverName.get(), ServerFieldType.ACTIVE, false);
    }
}
