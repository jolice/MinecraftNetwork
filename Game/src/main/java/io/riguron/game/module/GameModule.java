package io.riguron.game.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.game.map.GameMap;
import io.riguron.game.map.GameMaps;
import io.riguron.game.map.vote.GameMapVoting;
import io.riguron.game.timer.GameStartingCountdown;
import io.riguron.game.timer.SoundPlayingCountdown;
import io.riguron.game.config.GameOptions;
import io.riguron.game.core.Game;
import io.riguron.game.core.decorator.DelayedStartingGame;
import io.riguron.game.core.decorator.ImmediateStartingGame;
import io.riguron.game.core.decorator.StartStateChangingGame;
import io.riguron.game.core.type.AbstractGame;
import io.riguron.game.map.GameMap;
import io.riguron.game.map.GameMaps;
import io.riguron.game.map.vote.GameMapVoting;
import io.riguron.game.timer.GameStartingCountdown;
import io.riguron.game.timer.SoundPlayingCountdown;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.TimerFactory;
import io.riguron.system.task.timer.TaskOptions;
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
