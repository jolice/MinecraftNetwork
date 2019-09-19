package io.riguron.system.task.repeating;

/**
 * Represents a task that repeatedly executes some action at a certain time interval.
 * By default, implementations of this interface are not reusable, i.e task can not be
 * started again after it is stopped.
 *
 * @see ReusableRepeatingTask reusable implementation
 */
public interface RepeatingTask {

    /**
     * Starts repeating task. From now on, the task action will be executed repeatedly
     * until the task is stopped.
     */
    void start();

    /**
     * Terminates the task. Repeating action will no longer run (or will run the last time
     * after this method is executed).
     */
    void stop();

}
