package me.riguron.system.task.type;

import me.riguron.system.task.async.Callback;

/**
 * Represents a task that is performed either synchronously
 * or asynchronously and execution result is passed to the sync
 * or async callback.
 *
 * @param <T> type of the task execution result
 */
public interface CompletableTask<T> {

    /**
     * Runs task and executes callback with task result
     * asynchronously.
     *
     * @param result asynchronously executed callback
     */
    void completeAsynchronously(Callback<T> result);

    /**
     * Runs task and executes callback with task result
     * synchronously (in the main server thread)
     *
     * @param result synchronously executed callback
     */
    void completeSynchronously(Callback<T> result);

}
