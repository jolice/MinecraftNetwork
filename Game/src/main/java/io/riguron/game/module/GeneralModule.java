package io.riguron.game.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.name.Named;
import io.riguron.game.timer.StartManagingTimer;
import org.bukkit.Server;
import io.riguron.bukkit.stream.StreamMessageProvider;
import io.riguron.config.client.ConfigurationLoader;
import io.riguron.game.config.GameOptions;
import io.riguron.game.config.advanced.AdvancedGameOptions;
import io.riguron.game.core.Game;
import io.riguron.game.death.Respawn;
import io.riguron.game.message.GameMessageProvider;
import io.riguron.game.player.spectator.Spectator;
import io.riguron.game.timer.GameEndingCountdown;
import io.riguron.game.timer.GameStartingCountdown;
import io.riguron.game.timer.SoundPlayingCountdown;
import io.riguron.game.timer.StartManagingTimer;
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
