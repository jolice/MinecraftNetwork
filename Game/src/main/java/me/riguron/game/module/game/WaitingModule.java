package me.riguron.game.module.game;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import me.riguron.bukkit.item.join.JoinItems;
import me.riguron.bukkit.server.OnlineIndexer;
import me.riguron.game.config.GameOptions;
import me.riguron.game.listener.state.StateDependentListener;
import me.riguron.game.listener.state.waiting.*;
import me.riguron.game.listener.state.waiting.register.WaitingJoinRegistrationListener;
import me.riguron.game.listener.state.waiting.register.WaitingQuitUnregisterListener;
import me.riguron.game.player.GamePlayer;
import me.riguron.game.player.factory.GamePlayerFactory;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.system.stream.Broadcast;
import me.riguron.system.task.timer.Timer;
import org.bukkit.Server;

public class WaitingModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingJoinListener(@Named("GameStartingTimer") Timer gameStartingTimer, GameOptions gameOptions, Server server) {
        return new WaitingJoinListener(gameStartingTimer, gameOptions, server);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingJoinRegistrationListener(GamePlayerStorage<? super GamePlayer> gamePlayerStorage, GamePlayerFactory<? extends GamePlayer> gamePlayerFactory) {
        return new WaitingJoinRegistrationListener(gamePlayerStorage, gamePlayerFactory);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingLobbyListener() {
        return new WaitingLobbyListener();
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingQuitListener(@Named("GameStartingTimer") Timer bukkitTimer, GameOptions gameOptions, Broadcast broadcast, Server server) {
        return new WaitingQuitListener(bukkitTimer, gameOptions, broadcast, server);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingQuitUnregisterListener(GamePlayerStorage<?> gamePlayerStorage) {
        return new WaitingQuitUnregisterListener(gamePlayerStorage);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener moveListener(GameOptions gameOptions) {
        return new WaitingMoveListener(gameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingOnlineUpdater(OnlineIndexer indexer) {
        return new WaitingOnlineIndexer(indexer);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener waitingItemsListener(JoinItems joinItems) {
        return new WaitingItemsListener(joinItems);
    }
}

