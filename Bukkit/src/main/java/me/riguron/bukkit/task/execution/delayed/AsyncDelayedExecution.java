package me.riguron.bukkit.task.execution.delayed;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class AsyncDelayedExecution implements DelayedExecution {

    @Override
    public BukkitTask execute(Plugin plugin, BukkitScheduler scheduler, Runnable runnable, long delay) {
        return scheduler.runTaskLaterAsynchronously(plugin, runnable, delay);
    }
}
