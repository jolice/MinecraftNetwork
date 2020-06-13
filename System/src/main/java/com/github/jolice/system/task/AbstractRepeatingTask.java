package com.github.jolice.system.task;

import com.github.jolice.system.task.repeating.RepeatingTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbstractRepeatingTask implements RepeatingTask {

    private final SchedulerTask schedulerTask;

    @Override
    public void start() {
        assertState(schedulerTask.getTaskId() == -1, "Task is already running");
        schedulerTask.start();
    }

    @Override
    public void stop() {
        assertState(schedulerTask.getTaskId() != -1, "Task is not running");
        schedulerTask.cancel();
    }


    private void assertState(boolean expectedState, String message) {
        if (!expectedState) {
            throw new IllegalStateException(message);
        }
    }

}
