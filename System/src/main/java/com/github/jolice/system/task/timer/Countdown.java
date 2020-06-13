package com.github.jolice.system.task.timer;

import com.github.jolice.system.task.repeating.RepeatingAction;
import lombok.RequiredArgsConstructor;
import com.github.jolice.system.task.SchedulerTask;

@RequiredArgsConstructor
public class Countdown implements RepeatingAction {

    private final CountdownTask countdownTask;
    private final TaskOptions taskOptions;

    private long timesExecuted;

    @Override
    public void run(SchedulerTask owner) {
        countdownTask.onTick(++timesExecuted);
        if (timesExecuted == taskOptions.getTimesToRun()) {
            countdownTask.complete();
            owner.cancel();
        }
    }

}
