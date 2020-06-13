package com.github.jolice.system.task;

/**
 * Represents an action that runs repeatedly, at a fixed interval of time,
 * either synchronously or asynchronously.
 */
public interface FixedRepeatingAction extends Runnable {

    /**
     * The action.
     */
    void run();

    /**
     * Interval between the task executions
     *
     * @return interval of execution
     */
    int getInterval();
}
