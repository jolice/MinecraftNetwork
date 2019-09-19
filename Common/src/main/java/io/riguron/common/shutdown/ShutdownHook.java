package io.riguron.common.shutdown;

/**
 * Represents action that happens on server shutdown. It's typically
 * resource closing or any related stuff.
 */
public interface ShutdownHook extends Runnable {

    /**
     * Shutdown action logic.
     */
    void run();
}
