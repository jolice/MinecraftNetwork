package com.github.jolice.system.task.timer;

import com.github.jolice.system.task.SchedulerTask;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CountdownTest {

    @Test
    public void run() {
        CountdownTask countdownTask = mock(CountdownTask.class);
        TaskOptions taskOptions = mock(TaskOptions.class);
        when(taskOptions.getTimesToRun()).thenReturn(10L);

        SchedulerTask schedulerTask = mock(SchedulerTask.class);

        Countdown countdown = new Countdown(countdownTask, taskOptions);

        for (int i = 0; i < 10; i++) {
            countdown.run(schedulerTask);

            verify(countdownTask).onTick(i + 1);
            int times = i != 9 ? 0 : 1;

            verify(countdownTask, times(times)).complete();
            verify(schedulerTask, times(times)).cancel();

            reset(countdownTask);
        }
    }
}