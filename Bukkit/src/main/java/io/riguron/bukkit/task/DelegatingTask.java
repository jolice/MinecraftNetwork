package io.riguron.bukkit.task;

import io.riguron.system.task.ExecutionStrategy;
import io.riguron.system.task.Task;

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
