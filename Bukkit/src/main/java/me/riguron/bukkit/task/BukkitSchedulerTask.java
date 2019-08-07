package me.riguron.bukkit.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitScheduler;
import me.riguron.system.task.ExecutionStrategy;
import me.riguron.system.task.SchedulerTask;
import me.riguron.system.task.repeating.RepeatingAction;

@RequiredArgsConstructor
public class BukkitSchedulerTask implements SchedulerTask {

    private int bukkitTaskId = -1;

    private final BukkitScheduler bukkitScheduler;
    private final ExecutionStrategy repeatingStrategy;
    private final RepeatingAction repeatingAction;

    @Override
    public int getTaskId() {
        return bukkitTaskId;
    }

    @Override
    public void cancel() {
        bukkitScheduler.cancelTask(bukkitTaskId);
    }

    @Override
    public void start() {
        this.bukkitTaskId = repeatingStrategy.execute(() -> repeatingAction.run(this));
    }
}
