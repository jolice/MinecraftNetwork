package me.riguron.system.task.timer;

import me.riguron.system.task.repeating.RepeatingTask;

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
