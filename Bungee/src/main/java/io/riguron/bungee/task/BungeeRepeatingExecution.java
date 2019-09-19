package io.riguron.bungee.task;

import lombok.RequiredArgsConstructor;
import io.riguron.system.task.ExecutionStrategy;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class BungeeRepeatingExecution implements ExecutionStrategy {

    private final Plugin plugin;
    private final int interval;

    @Override
    public int execute(Runnable runnable) {

        return plugin.getProxy().getScheduler().schedule(
                plugin,
                runnable,
                0L,
                interval,
                TimeUnit.SECONDS
        ).getId();
    }
}
