package me.riguron.bukkit.task;

import me.riguron.system.task.ExecutionStrategy;
import me.riguron.system.task.Task;

public class DelegatingTask implements Task {

    private Runnable job;
    private ExecutionStrategy executionStrategy;

    public DelegatingTask(Runnable job, ExecutionStrategy executionStrategy) {
        this.job = job;
        this.executionStrategy = executionStrategy;
    }

    @Override
    public void execute() {
        executionStrategy.execute(job);
    }

}
