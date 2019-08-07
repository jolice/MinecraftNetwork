package me.riguron.system.task.repeating;

import me.riguron.system.task.SchedulerTask;

public interface RepeatingAction {

    /**
     * Tact of the task. This method represents the repeating
     * action each self.
     *
     * @param owner parent task, i.e action executor
     */
    void run(SchedulerTask owner);

}
