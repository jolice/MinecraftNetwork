package com.github.jolice.game.module;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.message.GameMessageProvider;
import com.github.jolice.game.player.spectator.Spectator;
import com.github.jolice.game.timer.GameEndingCountdown;
import com.github.jolice.game.timer.GameStartingCountdown;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.name.Named;
import com.github.jolice.game.timer.StartManagingTimer;
import org.bukkit.Server;
import com.github.jolice.stream.StreamMessageProvider;
import com.github.jolice.config.client.ConfigurationLoader;
import com.github.jolice.game.config.GameOptions;
import com.github.jolice.game.config.advanced.AdvancedGameOptions;
import com.github.jolice.game.death.Respawn;
import com.github.jolice.game.timer.SoundPlayingCountdown;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.TaskFactory;
import io.riguron.system.task.TimerFactory;
import io.riguron.system.task.timer.TaskOptions;
import io.riguron.system.task.timer.Timer;
import org.bukkit.plugin.Plugin;

public class GeneralModule extends AbstractModule {

    @Provides
    @Singleton
    @Named("GameStartingTimer")
    public Timer gameStartingTimer(TimerFactory timerFactory, Game game, GameOptions gameOptions, Broadcast broadcast) {
        return new StartManagingTimer(game, timerFactory.newTimer(new GameStartingCountdown(game, broadcast), new TaskOptions(gameOptions.getTimeToStart())));
    }

    @Provides
    @Singleton
    @Named("GameEndingTimer")
    public Timer gameEndingTimer(TimerFactory timerFactory, Game game, Broadcast broadcast, Server server, GameOptions gameOptions) {
        return timerFactory.newTimer(
                new SoundPlayingCountdown(
                        new GameEndingCountdown(game, broadcast), server),
                new TaskOptions(gameOptions.getExpirationTime()
                ));
    }

    @ProvidesIntoOptional(ProvidesIntoOptional.Type.ACTUAL)
    @Singleton
    public StreamMessageProvider streamMessageProvider(GameOptions gameOptions, Server server) {
        return new GameMessageProvider(gameOptions, server);
    }

    @Provides
    @Singleton
    public Spectator spectator(Plugin plugin, Server server) {
        return new Spectator(
               plugin, server
        );
    }

    @Provides
    @Singleton
    public Respawn respawn(Game game, TaskFactory taskFactory, GameOptions gameOptions) {
        return new Respawn(game, taskFactory, gameOptions);
    }

    @Provides
    @Singleton
    public AdvancedGameOptions advancedGameOptions(ConfigurationLoader configurationLoader) {
        return configurationLoader.load("advanced", AdvancedGameOptions.class);
    }





}
