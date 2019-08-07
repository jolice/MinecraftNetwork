package me.riguron.game.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.name.Named;
import org.bukkit.Server;
import me.riguron.bukkit.stream.StreamMessageProvider;
import me.riguron.config.client.ConfigurationLoader;
import me.riguron.game.config.GameOptions;
import me.riguron.game.config.advanced.AdvancedGameOptions;
import me.riguron.game.core.Game;
import me.riguron.game.death.Respawn;
import me.riguron.game.message.GameMessageProvider;
import me.riguron.game.player.spectator.Spectator;
import me.riguron.game.timer.GameEndingCountdown;
import me.riguron.game.timer.GameStartingCountdown;
import me.riguron.game.timer.SoundPlayingCountdown;
import me.riguron.game.timer.StartManagingTimer;
import me.riguron.system.stream.Broadcast;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.TimerFactory;
import me.riguron.system.task.timer.TaskOptions;
import me.riguron.system.task.timer.Timer;
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
