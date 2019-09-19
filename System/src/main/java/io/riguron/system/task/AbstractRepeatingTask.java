package io.riguron.system.task;

import lombok.RequiredArgsConstructor;
import io.riguron.system.task.repeating.RepeatingTask;

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
