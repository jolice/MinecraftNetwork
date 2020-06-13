package com.github.jolice.system.task.repeating;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * By default, {@link RepeatingTask RepeatingTask.class} is stateful, i.e
 * can not be reused. Once the task the stopped, it can not be started again.
 * This implementation is a decorator that allows for "reusing" a task multiple times.
 */
@RequiredArgsConstructor
public class ReusableRepeatingTask implements RepeatingTask {

    /**
     * Supplier that produces {@link RepeatingTask RepeatingTask.class} instances.
     */
    private final Supplier<RepeatingTask> taskProvider;

    private RepeatingTask currentTask;

    /**
     * Gets new task instance from the supplier and starts it. This method is reusable,
     * i.e can be called unlimited number times on the same instance.
     */
    @Override
    public void start() {
        this.currentTask = taskProvider.get();
        this.currentTask.start();
    }

    /**
     * Stops the task.  This method is reusable, i.e can be called unlimited number of times
     * on the same instance.
     */
    @Override
    public void stop() {
        this.currentTask.stop();
    }

}
