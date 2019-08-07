package me.riguron.game.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.game.config.GameOptions;
import me.riguron.game.core.Game;
import me.riguron.game.core.decorator.DelayedStartingGame;
import me.riguron.game.core.decorator.ImmediateStartingGame;
import me.riguron.game.core.decorator.StartStateChangingGame;
import me.riguron.game.core.type.AbstractGame;
import me.riguron.game.map.GameMap;
import me.riguron.game.map.GameMaps;
import me.riguron.game.map.vote.GameMapVoting;
import me.riguron.game.timer.GameStartingCountdown;
import me.riguron.game.timer.SoundPlayingCountdown;
import me.riguron.system.stream.Broadcast;
import me.riguron.system.task.TimerFactory;
import me.riguron.system.task.timer.TaskOptions;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class GameModule extends AbstractModule {

    private static final int ACTIVATION_TIME = 10;

    @Provides
    @Singleton
    public Game game(TimerFactory timerFactory, GameOptions gameOptions, GameMaps gameMaps, PluginManager pluginManager, Broadcast broadcast, Server server) {

        Game parentGame = new AbstractGame(gameMaps.getArbitraryMap(), pluginManager);

        if (gameOptions.isDelay()) {
            parentGame = new DelayedStartingGame(parentGame, timerFactory.newTimer(
                    new SoundPlayingCountdown(
                            new GameStartingCountdown(
                                    new StartStateChangingGame(parentGame),
                                    broadcast
                            ),
                            server
                    ),
                    new TaskOptions(ACTIVATION_TIME)));
        } else {
            parentGame = new ImmediateStartingGame(parentGame);
        }

        return parentGame;
    }

    @Provides
    @Singleton
    public GameMaps gameMaps(List<GameMap> gameMapList) {
        return new GameMaps(gameMapList);
    }

    @Provides
    @Singleton
    public GameMapVoting gameMapVoting(GameMaps gameMaps) {
        return new GameMapVoting(gameMaps);
    }

}
