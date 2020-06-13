package com.github.jolice.system.task.repeating;

import com.github.jolice.system.task.SchedulerTask;

public interface RepeatingAction {

    /**
     * Tact of the task. This method represents the repeating
     * action each self.
     *
     * @param owner parent task, i.e action executor
     */
    void run(SchedulerTask owner);

}
