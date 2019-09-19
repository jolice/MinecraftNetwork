package io.riguron.bukkit.task.execution;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import io.riguron.system.task.ExecutionStrategy;
import io.riguron.system.task.util.SecondsToTicks;

@RequiredArgsConstructor
public class RepeatingBukkitExecution implements ExecutionStrategy {

    private final Plugin plugin;
    private final BukkitScheduler bukkitScheduler;
    private final int delay;

    @Override
    public int execute(Runnable runnable) {
        return bukkitScheduler.runTaskTimer(plugin, runnable, 0L, new SecondsToTicks(delay).convert()).getTaskId();

    }
}
