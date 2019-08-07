package me.riguron.system.task;

import me.riguron.system.task.timer.CountdownTask;
import me.riguron.system.task.timer.TaskOptions;
import me.riguron.system.task.timer.Timer;

public interface TimerFactory {

    Timer newTimer(CountdownTask countdownTask, TaskOptions taskOptions);
}
