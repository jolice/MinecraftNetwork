package com.github.jolice.system.task.repeating;

import lombok.RequiredArgsConstructor;
import com.github.jolice.system.task.SchedulerTask;

@RequiredArgsConstructor
public class RepeatingRunnable implements RepeatingAction {

    private final Runnable runnable;

    @Override
    public void run(SchedulerTask owner) {
        this.runnable.run();
    }
}
