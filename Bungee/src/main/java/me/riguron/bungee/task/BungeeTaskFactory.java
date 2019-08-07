package me.riguron.bungee.task;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;
import me.riguron.system.task.AbstractRepeatingTask;
import me.riguron.system.task.Task;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.async.Callback;
import me.riguron.system.task.repeating.RepeatingRunnable;
import me.riguron.system.task.repeating.RepeatingTask;
import me.riguron.system.task.type.CompletableTask;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class BungeeTaskFactory implements TaskFactory {

    private final TaskScheduler taskScheduler;
    private final Plugin plugin;

    @Override
    public Task newSyncTask(Runnable runnable) {
        return failSync();
    }

    @Override
    public Task newSyncDelayedTask(Runnable runnable, int delayInSeconds) {
        return failSync();
    }

    @Override
    public Task newAsyncDelayedTask(Runnable runnable, int delayInSeconds) {
        return () -> taskScheduler.schedule(plugin, runnable, delayInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public <R> CompletableTask<R> newAsyncFunction(Supplier<R> task) {
        return new CompletableTask<R>() {
            @Override
            public void completeAsynchronously(Callback<R> result) {
                taskScheduler.runAsync(plugin, () -> result.call(task.get()));
            }

            @Override
            public void completeSynchronously(Callback<R> result) {
                failSync();
            }
        };
    }

    @Override
    public Task newAsyncTask(Runnable runnable) {
        return () -> taskScheduler.runAsync(plugin, runnable);
    }

    @Override
    public RepeatingTask newRepeatingTask(Runnable runnable, int period) {
        return new AbstractRepeatingTask(
                new BungeeSchedulerTask(
                        new BungeeRepeatingExecution(plugin, period),
                        taskScheduler,
                        new RepeatingRunnable(runnable)
                )
        );
    }

    private <T> T failSync() {
        throw new UnsupportedOperationException("BungeeCord does not support synchronous tasks!");
    }
}
