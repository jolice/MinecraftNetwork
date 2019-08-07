package me.riguron.bukkit.task;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import me.riguron.system.task.async.Callback;
import me.riguron.system.task.type.CompletableTask;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class AsyncFunction<T> implements CompletableTask<T> {

    private Callback<T> successHandler;
    private Supplier<T> job;
    private Plugin plugin;

    public AsyncFunction(Plugin plugin, Supplier<T> job) {
        this.plugin = plugin;
        this.job = job;
    }

    @Override
    public void completeAsynchronously(Callback<T> successHandler) {
        this.complete((bukkitScheduler, runnable) -> bukkitScheduler.runTaskAsynchronously(plugin, runnable));
        this.successHandler = successHandler;
    }

    @Override
    public void completeSynchronously(Callback<T> successHandler) {
        this.complete((bukkitScheduler, runnable) -> bukkitScheduler.runTask(plugin, runnable));
        this.successHandler = successHandler;
    }

    private void complete(BiConsumer<BukkitScheduler, Runnable> executionPlan) {
        final BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.runTaskAsynchronously(plugin, () -> {
            final T taskResult = job.get();
            executionPlan.accept(scheduler, () -> successHandler.call(taskResult));
        });
    }

}
