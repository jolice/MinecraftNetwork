package com.github.jolice.game.module;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameDeathType;
import com.github.jolice.game.death.handler.DropOutDeathHandler;
import com.github.jolice.game.death.handler.LivesDeathHandler;
import com.github.jolice.game.death.handler.RespawnDeathHandler;
import com.github.jolice.game.listener.ListenerRegistration;
import com.github.jolice.game.listener.ListenerRegistrationService;
import com.github.jolice.game.listener.ListenerUnregister;
import com.github.jolice.game.listener.change.GameCountdownListener;
import com.github.jolice.game.listener.change.GameEndListener;
import com.github.jolice.game.listener.restrictions.RestrictionsModule;
import com.github.jolice.game.listener.start.GameStartListener;
import com.github.jolice.game.listener.state.StateDependentListener;
import com.github.jolice.game.listener.state.TriggeringStateChangeListener;
import com.github.jolice.game.listener.state.active.DropoutQuitListener;
import com.github.jolice.game.map.vote.GameMapVoting;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.status.PlayerLives;
import com.github.jolice.game.server.GameStartUpdater;
import com.github.jolice.game.winner.WinningHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import com.github.jolice.game.death.DeathListener;
import com.github.jolice.game.death.Respawn;
import com.github.jolice.game.death.handler.PlayerDeathHandler;
import com.github.jolice.game.config.GameOptions;
import io.riguron.server.ServerProfile;
import io.riguron.server.repository.OnlineIndexingRepository;
import io.riguron.server.repository.ServerRepository;
import io.riguron.system.listener.ConditionalListener;
import io.riguron.system.stream.Broadcast;
import io.riguron.system.task.TaskFactory;
import io.riguron.system.task.timer.Timer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RestrictionsModule());
    }

    @ProvidesIntoSet
    @Singleton
    public Listener gameStateListener(ListenerRegistrationService listenerRegistrationService) {
        return new TriggeringStateChangeListener(listenerRegistrationService);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener gameStartListener(@Named("GameEndingTimer") Timer timer, Game game) {
        return new GameStartListener(timer, game);
    }

    @Provides
    @Singleton
    public ListenerRegistrationService eventRegistrationService(Plugin plugin, PluginManager pluginManager, Set<StateDependentListener> stateDependentListeners, ListenerUnregister listenerUnregister) {
        List<ListenerRegistration> listenerRegistrations = stateDependentListeners
                .stream()
                .map(stateDependentListener -> new ListenerRegistration(stateDependentListener, stateDependentListener.states()))
                .collect(Collectors.toList());
        return new ListenerRegistrationService(plugin, pluginManager, listenerRegistrations, listenerUnregister);
    }

    @Provides
    @Singleton
    public ListenerUnregister listenerUnregister() {
        return new ListenerUnregister();
    }


    @ProvidesIntoSet
    @Singleton
    public Listener gameEndListener(WinningHandler winningHandler) {
        return new GameEndListener(winningHandler);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener gameCountdownListener(GameMapVoting gameMapVoting, Broadcast broadcast, ServerRepository serverRepository) {
        return new GameCountdownListener(gameMapVoting, broadcast, serverRepository);
    }

    @ProvidesIntoOptional(value = ProvidesIntoOptional.Type.DEFAULT)
    @Singleton
    public PlayerDeathHandler deathHandler(GameOptions gameOptions, Respawn respawn, GamePlayerStorage<?> gamePlayerStorage, PlayerLives playerLives) {
        switch (gameOptions.getDeathType()) {
            case LIVES:
                return new LivesDeathHandler(playerLives, gamePlayerStorage, respawn);
            case RESPAWN:
                return new RespawnDeathHandler(respawn, gamePlayerStorage);
            case DROP_OUT:
                return new DropOutDeathHandler(gamePlayerStorage);
            default:
                throw new IllegalStateException(String.valueOf(gameOptions.getDeathType()));
        }
    }

    @ProvidesIntoSet
    @Singleton
    public ConditionalListener<Listener> activeDeathListener(GamePlayerStorage<?> gamePlayerStorage, GameOptions gameOptions) {
        return new ConditionalListener<>(new DropoutQuitListener(gamePlayerStorage), gameOptions.getDeathType().equals(GameDeathType.RESPAWN));
    }

    @ProvidesIntoSet
    @Singleton
    public Listener deathListener(PlayerDeathHandler playerDeathHandler) {
        return new DeathListener(playerDeathHandler);
    }

    @ProvidesIntoSet
    @Singleton
    public Listener onlineUnregisterListener(OnlineIndexingRepository repository, ServerProfile serverGroup, ServerRepository serverRepository, TaskFactory taskFactory) {
        return new GameStartUpdater(repository, serverGroup, serverRepository, taskFactory);
    }

}
