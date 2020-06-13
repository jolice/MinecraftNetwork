package com.github.jolice.game.module;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.listener.dropout.SoloDropOutListener;
import com.github.jolice.game.map.GameMap;
import com.github.jolice.game.map.type.SoloGameMap;
import com.github.jolice.game.player.GamePlayer;
import com.github.jolice.game.player.NullPlayer;
import com.github.jolice.game.player.factory.GamePlayerFactory;
import com.github.jolice.game.player.factory.StandardPlayerFactory;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.repository.ValidatingGamePlayerStorage;
import com.github.jolice.game.player.repository.VirtualGamePlayerStorage;
import com.github.jolice.game.winner.WinningHandler;
import com.github.jolice.game.winner.solo.OrderedResultCalculation;
import com.github.jolice.game.winner.solo.SoloResultCalculation;
import com.github.jolice.game.winner.solo.SoloWinningHandler;
import com.github.jolice.game.winner.solo.order.PlaceOrderByScore;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.github.jolice.config.client.ConfigurationLoader;
import com.github.jolice.game.config.GameOptions;
import com.github.jolice.game.config.map.solo.SoloMapConfig;
import io.riguron.system.stream.Broadcast;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.stream.Collectors;

public class SoloModule extends AbstractModule {

    @Provides
    @Singleton
    public GamePlayerStorage<? super GamePlayer> gamePlayerRepository() {
        return new ValidatingGamePlayerStorage<>(new VirtualGamePlayerStorage<>(new NullPlayer()));
    }

    @Provides
    @Singleton
    public GamePlayerStorage<?> gamePlayerStorage(GamePlayerStorage<? super GamePlayer> gamePlayerStorage) {
        return gamePlayerStorage;
    }

    @Provides
    @Singleton
    public GameOptions gameOptions(ConfigurationLoader configurationLoader) {
        return configurationLoader.load("game", GameOptions.class);
    }

    @Provides
    public List<GameMap> soloMaps(ConfigurationLoader configurationLoader, GamePlayerStorage<?> gamePlayerStorage) {
        return configurationLoader
                .loadAll("maps", SoloMapConfig.class)
                .stream()
                .map(soloMapConfig -> new SoloGameMap(soloMapConfig.getName(), soloMapConfig.getSpawnLocations(), gamePlayerStorage))
                .collect(Collectors.toList());
    }

    @Provides
    @Singleton
    public GamePlayerFactory<? extends GamePlayer> gamePlayerFactory() {
        return new StandardPlayerFactory();
    }

    @Provides
    @Singleton
    public WinningHandler winningHandler(SoloResultCalculation soloResultCalculation, Broadcast broadcast) {
        return new SoloWinningHandler(soloResultCalculation, broadcast);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener soloDropOutListener(Game game, GamePlayerStorage<?> gamePlayerStorage) {
        return new SoloDropOutListener(game, gamePlayerStorage);
    }

    @Provides
    @Singleton
    public SoloResultCalculation soloResultCalculation(GamePlayerStorage<?> gamePlayerStorage) {
        return new OrderedResultCalculation(gamePlayerStorage, new PlaceOrderByScore());
    }
}
