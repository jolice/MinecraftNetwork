package io.riguron.system.task.startup;

import io.riguron.common.shutdown.ShutdownHook;

/**
 * A task that runs right after the server startup. Players are not able to join
 * the server until all tasks are completed.
 *
 * @see ShutdownHook an antonym
 */
public interface PostLoadTask {

    void run();

}
