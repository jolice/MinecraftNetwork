package io.riguron.system.task.timer;

import io.riguron.system.task.repeating.RepeatingTask;
import io.riguron.system.task.repeating.RepeatingTask;

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
