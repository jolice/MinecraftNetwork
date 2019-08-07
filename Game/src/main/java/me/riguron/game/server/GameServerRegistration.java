package me.riguron.game.server;

import lombok.RequiredArgsConstructor;
import me.riguron.bukkit.server.ServerRegistration;
import me.riguron.game.config.GameOptions;
import me.riguron.game.core.Game;
import me.riguron.server.mapper.GameServerMapper;
import me.riguron.server.mapper.StandardServerMapper;
import me.riguron.server.repository.ServerRepository;
import me.riguron.server.type.GameServer;
import me.riguron.server.type.Server;

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
