package com.github.jolice.system.task.timer;

import com.github.jolice.system.task.repeating.RepeatingTask;

/**
 * {@inheritDoc}
 */
public interface Timer extends RepeatingTask {

    /**
     * {@inheritDoc}
     */
    void start();

    /**
     * {@inheritDoc}
     */
    void stop();
}
