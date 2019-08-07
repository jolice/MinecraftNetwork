package me.riguron.game.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.config.client.ConfigurationLoader;
import me.riguron.game.config.GameOptions;
import me.riguron.game.config.map.solo.SoloMapConfig;
import me.riguron.game.core.Game;
import me.riguron.game.listener.dropout.SoloDropOutListener;
import me.riguron.game.map.GameMap;
import me.riguron.game.map.type.SoloGameMap;
import me.riguron.game.player.GamePlayer;
import me.riguron.game.player.NullPlayer;
import me.riguron.game.player.factory.GamePlayerFactory;
import me.riguron.game.player.factory.StandardPlayerFactory;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.repository.ValidatingGamePlayerStorage;
import me.riguron.game.player.repository.VirtualGamePlayerStorage;
import me.riguron.game.winner.WinningHandler;
import me.riguron.game.winner.solo.OrderedResultCalculation;
import me.riguron.game.winner.solo.SoloResultCalculation;
import me.riguron.game.winner.solo.SoloWinningHandler;
import me.riguron.game.winner.solo.order.PlaceOrderByScore;
import me.riguron.system.stream.Broadcast;
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
