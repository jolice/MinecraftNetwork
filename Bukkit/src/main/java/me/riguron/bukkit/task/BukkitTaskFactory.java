package me.riguron.bukkit.task;

import lombok.RequiredArgsConstructor;
import me.riguron.bukkit.task.execution.DelayedBukkitExecution;
import me.riguron.bukkit.task.execution.ImmediateBukkitExecution;
import me.riguron.bukkit.task.execution.RepeatingBukkitExecution;
import me.riguron.bukkit.task.execution.delayed.AsyncDelayedExecution;
import me.riguron.bukkit.task.execution.delayed.DelayedExecution;
import me.riguron.bukkit.task.execution.delayed.SyncDelayedExecution;
import me.riguron.system.task.AbstractRepeatingTask;
import me.riguron.system.task.Task;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.repeating.RepeatingRunnable;
import me.riguron.system.task.repeating.RepeatingTask;
import me.riguron.system.task.type.CompletableTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class BukkitTaskFactory implements TaskFactory {

    private final Plugin plugin;
    private final BukkitScheduler bukkitScheduler;

    @Override
    public Task newSyncTask(Runnable runnable) {
        return new DelegatingTask(runnable, new ImmediateBukkitExecution(plugin, bukkitScheduler));
    }

    @Override
    public Task newAsyncDelayedTask(Runnable runnable, int delayInSeconds) {
        return newDelayedTask(runnable, new AsyncDelayedExecution(), delayInSeconds);
    }

    @Override
    public Task newSyncDelayedTask(Runnable runnable, int delayInSeconds) {
        return newDelayedTask(runnable, new SyncDelayedExecution(), delayInSeconds);
    }

    @Override
    public <R> CompletableTask<R> newAsyncFunction(Supplier<R> task) {
        return new AsyncFunction<>(plugin, task);
    }

    @Override
    public Task newAsyncTask(Runnable runnable) {
        return new AsyncProcedure(runnable, plugin);

    }

    @Override
    public RepeatingTask newRepeatingTask(Runnable runnable, int period) {
        return new AbstractRepeatingTask(
                new BukkitSchedulerTask(
                        bukkitScheduler,
                        new RepeatingBukkitExecution(plugin, bukkitScheduler, period),
                        new RepeatingRunnable(runnable)
                )
        );
    }

    private Task newDelayedTask(Runnable runnable, DelayedExecution delayedExecution, int delay) {
        return new DelegatingTask(runnable, new DelayedBukkitExecution(plugin, delayedExecution, delay));
    }
}
