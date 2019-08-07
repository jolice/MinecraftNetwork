package me.riguron.game.server;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.bukkit.server.ServerRegistration;
import me.riguron.common.shutdown.ShutdownHook;
import me.riguron.game.config.GameOptions;
import me.riguron.game.core.Game;
import me.riguron.server.ServerName;
import me.riguron.server.repository.ServerRepository;
import me.riguron.server.type.Server;

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
