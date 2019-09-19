package io.riguron.game.server;

import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.server.ServerRegistration;
import io.riguron.game.config.GameOptions;
import io.riguron.game.core.Game;
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
