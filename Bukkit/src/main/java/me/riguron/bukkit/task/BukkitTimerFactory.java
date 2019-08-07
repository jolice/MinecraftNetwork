package me.riguron.bukkit.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import me.riguron.bukkit.task.execution.RepeatingBukkitExecution;
import me.riguron.system.task.AbstractRepeatingTask;
import me.riguron.system.task.TimerFactory;
import me.riguron.system.task.repeating.ReusableRepeatingTask;
import me.riguron.system.task.timer.*;

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
