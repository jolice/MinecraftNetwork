package com.github.jolice.game.server;

import com.github.jolice.game.core.Game;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.github.jolice.game.config.GameOptions;
import com.github.jolice.server.ServerRegistration;
import com.github.jolice.common.shutdown.ShutdownHook;
import io.riguron.server.ServerName;
import io.riguron.server.repository.ServerRepository;
import io.riguron.server.type.Server;

public class GameServerDataModule extends AbstractModule {

    @ProvidesIntoOptional(value = ProvidesIntoOptional.Type.ACTUAL)
    @Singleton
    public ServerRegistration serverRegistration(ServerRepository serverRepository, Server server, Game game, GameOptions gameOptions) {
        return new GameServerRegistration(serverRepository, server, gameOptions, game);
    }

    @ProvidesIntoSet
    @Singleton
    public ShutdownHook gameServerUnregister(ServerRepository serverRepository, ServerName serverName) {
        return new GameServerUnregister(serverRepository, serverName);
    }

}
