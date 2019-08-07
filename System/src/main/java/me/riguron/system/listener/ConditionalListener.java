package me.riguron.system.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

/**
 * Represents listener that must be registered only if a certain condition
 * evaluates to true.
 *
 * @param <T> type of the listener
 */
@Value
@Getter(AccessLevel.NONE)
public class ConditionalListener<T> {

    private T listener;

    /**
     * Condition that must match true to register the listener
     */
    private boolean condition;

    public T getListener() {
        return listener;
    }

    public boolean register() {
        return condition;
    }
}
