package io.riguron.bukkit.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import io.riguron.bukkit.task.execution.RepeatingBukkitExecution;
import io.riguron.system.task.AbstractRepeatingTask;
import io.riguron.system.task.TimerFactory;
import io.riguron.system.task.repeating.ReusableRepeatingTask;
import io.riguron.system.task.timer.*;

@RequiredArgsConstructor
public class BukkitTimerFactory implements TimerFactory {

    private final Plugin plugin;
    private final BukkitScheduler bukkitScheduler;

    @Override
    public Timer newTimer(CountdownTask countdownTask, TaskOptions taskOptions) {
        return new TimerAdapter(
                new ReusableRepeatingTask(
                        () -> new AbstractRepeatingTask(
                                new BukkitSchedulerTask(
                                        bukkitScheduler,
                                        new RepeatingBukkitExecution(plugin, bukkitScheduler, taskOptions.getInterval()),
                                        new Countdown(countdownTask, taskOptions)
                                )
                        )
                )
        );
    }
}
