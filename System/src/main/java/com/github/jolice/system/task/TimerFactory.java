package com.github.jolice.system.task;

import com.github.jolice.system.task.timer.CountdownTask;
import com.github.jolice.system.task.timer.TaskOptions;
import com.github.jolice.system.task.timer.Timer;

public interface TimerFactory {

    Timer newTimer(CountdownTask countdownTask, TaskOptions taskOptions);
}
