package com.github.jolice.system.task;

/**
 * A wrapper for native scheduler task.
 */
public interface SchedulerTask {

    /**
     * Returns a task ID from native scheduler, or 0
     * if the task is not started yet.
     *
     * @return native task id
     */
    int getTaskId();

    /**
     * Stops the task.
     */
    void cancel();

    /**
     * Starts the task and assigns the task ID.
     */
    void start();

}
