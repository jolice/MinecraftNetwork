package io.riguron.bukkit.task.execution;

import io.riguron.bukkit.task.execution.delayed.DelayedExecution;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import io.riguron.bukkit.task.execution.delayed.DelayedExecution;
import io.riguron.system.task.ExecutionStrategy;
import io.riguron.system.task.util.SecondsToTicks;

@RequiredArgsConstructor
public class DelayedBukkitExecution implements ExecutionStrategy {

    private final Plugin plugin;
    private final DelayedExecution delayedExecution;
    private final int delayInSeconds;

    @Override
    public int execute(Runnable runnable) {
        return delayedExecution.execute(plugin, plugin.getServer().getScheduler(), runnable, new SecondsToTicks(delayInSeconds).convert()).getTaskId();
    }
}
