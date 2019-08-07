package me.riguron.bukkit.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import me.riguron.system.task.Task;

@RequiredArgsConstructor
public class AsyncProcedure implements Task {

    private final Runnable job;
    private final Plugin plugin;

    @Override
    public void execute() {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, job);
    }
}
