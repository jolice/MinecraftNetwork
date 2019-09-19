package io.riguron.game.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import io.riguron.game.core.GameState;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Each game state has its own listeners that are active only when
 * the game is in that state. For example, waiting state requires join and lobby listeners,
 * and active state needs death listener. This class is responsible for managing game listeners.
 */
public class ListenerRegistrationService {

    private Plugin plugin;
    private PluginManager pluginManager;
    private ListenerUnregister listenerUnregister;

    private Set<Listener> registeredListeners = new HashSet<>();
    private Map<GameState, Set<Listener>> stateListeners = new EnumMap<>(GameState.class);

    public ListenerRegistrationService(Plugin plugin, PluginManager pluginManager, List<ListenerRegistration> registrationListeners, ListenerUnregister listenerUnregister) {
        this.pluginManager = pluginManager;
        this.plugin = plugin;
        this.listenerUnregister = listenerUnregister;
        this.collectInitially(registrationListeners);
    }

    /**
     * Registers listeners for new game state, and unregisters listeners that are no longer
     * actual for the new state (listeners of the previous state).
     *
     * @param newState new game state
     */
    public void gameStateChanged(GameState newState) {
        Set<Listener> listenersForNewState = recalculateListenersForNewState(newState);
        this.registerNew(listenersForNewState);
    }

    private Set<Listener> recalculateListenersForNewState(GameState newState) {
        Set<Listener> listenersForNewState = stateListeners.getOrDefault(newState, Collections.emptySet());
        this.unregisterOld(listenersForNewState);
        return filterAlreadyRegistered(listenersForNewState);
    }

    private void unregisterOld(Set<Listener> listenersForNewState) {
        Set<Listener> toRemove = registeredListeners
                .stream()
                .filter(x -> !listenersForNewState.contains(x))
                .collect(Collectors.toSet());
        toRemove.forEach(listenerUnregister::unregister);
        this.registeredListeners.removeAll(toRemove);
    }

    private Set<Listener> filterAlreadyRegistered(Set<Listener> listenersForNewState) {
        return listenersForNewState
                .stream()
                .filter(listener -> !registeredListeners.contains(listener))
                .collect(Collectors.toSet());
    }


    private void registerNew(Set<Listener> listenersForNewState) {
        listenersForNewState.forEach(listener -> pluginManager.registerEvents(listener, plugin));
        this.registeredListeners.addAll(listenersForNewState);
    }

    private void collectInitially(List<ListenerRegistration> registrationListeners) {
        for (ListenerRegistration listenerRegistration : registrationListeners) {
            listenerRegistration.getStates().
                    forEach(gameState -> stateListeners.computeIfAbsent(gameState,
                            x -> new HashSet<>()).add(listenerRegistration.getListener()));
        }
    }
}
