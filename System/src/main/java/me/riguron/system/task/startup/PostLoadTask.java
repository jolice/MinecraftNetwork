package me.riguron.system.task.startup;

/**
 * A task that runs right after the server startup. Players are not able to join
 * the server until all tasks are completed.
 *
 * @see me.riguron.common.shutdown.ShutdownHook an antonym
 */
public interface PostLoadTask {

    void run();

}
