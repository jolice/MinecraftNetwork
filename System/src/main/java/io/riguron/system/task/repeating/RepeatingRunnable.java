package io.riguron.system.task.repeating;

import lombok.RequiredArgsConstructor;
import io.riguron.system.task.SchedulerTask;

@RequiredArgsConstructor
public class RepeatingRunnable implements RepeatingAction {

    private final Runnable runnable;

    @Override
    public void run(SchedulerTask owner) {
        this.runnable.run();
    }
}
