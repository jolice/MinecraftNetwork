package me.riguron.system.listener;

import java.util.Collection;

/**
 * Implementations of this interface are responsible of registering listeners.
 * It's typically done by delegating the registration operation to the underlying
 * environment API.
 *
 * @param <T> type of the Listener interface, depending on the environment
 */
public interface ListenerRegistration<T> {

    void registerListener(T listener);

    default void registerAll(Collection<T> listeners) {
        listeners.forEach(this::registerListener);
    }
}
