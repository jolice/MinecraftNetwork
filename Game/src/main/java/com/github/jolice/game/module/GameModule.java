package com.github.jolice.game.module;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.decorator.DelayedStartingGame;
import com.github.jolice.game.core.decorator.ImmediateStartingGame;
import com.github.jolice.game.core.decorator.StartStateChangingGame;
import com.github.jolice.game.core.type.AbstractGame;
import com.github.jolice.game.map.GameMap;
import com.github.jolice.game.map.GameMaps;
import com.github.jolice.game.map.vote.GameMapVoting;
import com.github.jolice.game.timer.GameStartingCountdown;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.game.timer.SoundPlayingCountdown;
import com.github.jolice.game.config.GameOptions;
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
