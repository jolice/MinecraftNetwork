package me.riguron.bukkit.task.execution.delayed;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * Represents deferred action.
 */
public interface DelayedExecution {

    BukkitTask execute(Plugin plugin, BukkitScheduler scheduler, Runnable runnable, long delay);
}
