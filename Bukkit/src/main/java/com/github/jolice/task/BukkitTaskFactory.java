package com.github.jolice.task;

import com.github.jolice.task.execution.DelayedBukkitExecution;
import com.github.jolice.task.execution.ImmediateBukkitExecution;
import com.github.jolice.task.execution.RepeatingBukkitExecution;
import com.github.jolice.task.execution.delayed.AsyncDelayedExecution;
import com.github.jolice.task.execution.delayed.DelayedExecution;
import com.github.jolice.task.execution.delayed.SyncDelayedExecution;
import lombok.RequiredArgsConstructor;
import io.riguron.system.task.AbstractRepeatingTask;
import io.riguron.system.task.Task;
import io.riguron.system.task.TaskFactory;
import io.riguron.system.task.repeating.RepeatingRunnable;
import io.riguron.system.task.repeating.RepeatingTask;
import io.riguron.system.task.type.CompletableTask;
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
