package me.riguron.game.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import me.riguron.game.config.GameOptions;
import me.riguron.game.core.Game;
import me.riguron.game.core.GameDeathType;
import me.riguron.game.death.DeathListener;
import me.riguron.game.death.Respawn;
import me.riguron.game.death.handler.DropOutDeathHandler;
import me.riguron.game.death.handler.LivesDeathHandler;
import me.riguron.game.death.handler.PlayerDeathHandler;
import me.riguron.game.death.handler.RespawnDeathHandler;
import me.riguron.game.listener.ListenerRegistrationService;
import me.riguron.game.listener.ListenerRegistration;
import me.riguron.game.listener.ListenerUnregister;
import me.riguron.game.listener.change.GameCountdownListener;
import me.riguron.game.listener.change.GameEndListener;
import me.riguron.game.listener.restrictions.RestrictionsModule;
import me.riguron.game.listener.start.GameStartListener;
import me.riguron.game.listener.state.StateDependentListener;
import me.riguron.game.listener.state.TriggeringStateChangeListener;
import me.riguron.game.listener.state.active.DropoutQuitListener;
import me.riguron.game.map.vote.GameMapVoting;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.status.PlayerLives;
import me.riguron.game.server.GameStartUpdater;
import me.riguron.game.winner.WinningHandler;
import me.riguron.server.ServerProfile;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.server.repository.ServerRepository;
import me.riguron.system.listener.ConditionalListener;
import me.riguron.system.stream.Broadcast;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.timer.Timer;
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
