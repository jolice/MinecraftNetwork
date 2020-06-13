package com.github.jolice.game.server;

import com.github.jolice.game.core.Game;
import lombok.RequiredArgsConstructor;
import com.github.jolice.server.ServerRegistration;
import com.github.jolice.game.config.GameOptions;
import io.riguron.server.mapper.GameServerMapper;
import io.riguron.server.mapper.StandardServerMapper;
import io.riguron.server.repository.ServerRepository;
import io.riguron.server.type.GameServer;
import io.riguron.server.type.Server;

@RequiredArgsConstructor
public class GameServerRegistration implements ServerRegistration {

    private final ServerRepository serverRepository;
    private final Server server;
    private final GameOptions gameOptions;
    private final Game game;

    @Override
    public void run() {
        serverRepository.addServer(new GameServer(server,
                game.getMap().getName(), gameOptions.getGameName(), false), new GameServerMapper(new StandardServerMapper()));
    }
}
