package com.github.jolice.system.listener;

import com.google.inject.Inject;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class responsible for registering listeners declared in distinct Guice modules.
 *
 * @param <T> type of the listener interface. Differs depending on environment.
 */
public class ListenerPostProcessor<T> {

    private ListenerRegistration<T> registration;

    @Inject
    public ListenerPostProcessor(ListenerRegistration<T> registration) {
        this.registration = registration;
    }

    /**
     * Registers conditional listeners.
     *
     * @param conditionalListeners set of conditional listeners
     */
    @Inject(optional = true)
    public void registerConditionals(Set<ConditionalListener<T>> conditionalListeners) {
        registration.registerAll(conditionalListeners
                .stream()
                .filter(ConditionalListener::register)
                .map(ConditionalListener::getListener)
                .collect(Collectors.toSet()));
    }

    /**
     * Registers plain listeners.
     *
     * @param set declared listeners
     */
    @Inject(optional = true)
    public void register(Set<T> set) {
        set.forEach(x -> registration.registerListener(x));
    }
}
