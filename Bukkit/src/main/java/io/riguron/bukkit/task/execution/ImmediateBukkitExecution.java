package io.riguron.bukkit.task.execution;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import io.riguron.system.task.ExecutionStrategy;

@RequiredArgsConstructor
public class ImmediateBukkitExecution implements ExecutionStrategy {

    private final Plugin plugin;
    private final BukkitScheduler bukkitScheduler;

    @Override
    public int execute(Runnable runnable) {
        return bukkitScheduler.runTask(plugin, runnable).getTaskId();
    }
}
