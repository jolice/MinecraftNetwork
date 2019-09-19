package io.riguron.system.task;

import io.riguron.system.task.timer.CountdownTask;
import io.riguron.system.task.timer.TaskOptions;
import io.riguron.system.task.timer.Timer;

public interface TimerFactory {

    Timer newTimer(CountdownTask countdownTask, TaskOptions taskOptions);
}
