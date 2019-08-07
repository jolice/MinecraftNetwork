package me.riguron.system.task.timer;

import lombok.RequiredArgsConstructor;
import me.riguron.system.task.SchedulerTask;
import me.riguron.system.task.repeating.RepeatingAction;

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
