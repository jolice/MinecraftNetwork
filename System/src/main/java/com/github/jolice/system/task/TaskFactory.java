package com.github.jolice.system.task;

import com.github.jolice.system.task.repeating.RepeatingTask;
import com.github.jolice.system.task.type.CompletableTask;

import java.util.function.Supplier;

/**
 * Notice that task factory is responsible only for creating task and not running them.
 * Task must be started manually by executing an appropriate method.
 */
public interface TaskFactory {

    /**
     * Creates a task that is executed in a main server thread,
     * i.e synchronously. Sync execution may not be supported in some
     * implementations.
     *
     * @param runnable task body
     * @return created task
     */
    Task newSyncTask(Runnable runnable);

    /**
     * Creates a task that is executed asynchronously after a fixed
     * delay (since the task start).
     *
     * @param runnable       task
     * @param delayInSeconds the delay between the task start and actual task execution
     * @return asynchronous delayed task
     */
    Task newAsyncDelayedTask(Runnable runnable, int delayInSeconds);

    /**
     * Creates a task that is executed synchronously after a fixed
     * delay (since the task start).
     *
     * @param runnable       task
     * @param delayInSeconds the delay between the task start and actual task execution
     * @return synchronous delayed task
     */
    Task newSyncDelayedTask(Runnable runnable, int delayInSeconds);

    /**
     * Creates a completable task.
     *
     * @param task task result producer
     * @param <R>  type of the task result
     * @return completable task instance
     * @see CompletableTask
     */
    <R> CompletableTask<R> newAsyncFunction(Supplier<R> task);

    /**
     * Creates an asynchronous task that runs encapsulated action
     * immediately after the task start.
     *
     * @param runnable task
     * @return immediate asynchronous task
     */
    Task newAsyncTask(Runnable runnable);

    /**
     * Creates a repeating task (i.e a task that runs repeatedly with fixed
     * interval)
     *
     * @param runnable actual task
     * @param period   delay between task executions
     * @return repeating task with fixed interval
     */
    RepeatingTask newRepeatingTask(Runnable runnable, int period);

}
