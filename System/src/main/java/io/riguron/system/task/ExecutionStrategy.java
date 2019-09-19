package io.riguron.system.task;

public interface ExecutionStrategy {

    /**
     * Executes task.
     *
     * @param runnable task
     * @return task id returned by the native scheduler
     */
    int execute(Runnable runnable);
}
