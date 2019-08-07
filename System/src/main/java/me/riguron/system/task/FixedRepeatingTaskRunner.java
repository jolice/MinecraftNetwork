package me.riguron.system.task;

import com.google.inject.Inject;

import java.util.Set;

/**
 * Class responsible for starting fixed repeating task after the server startup.
 */
public class FixedRepeatingTaskRunner {

    private final TaskFactory taskFactory;

    @Inject
    public FixedRepeatingTaskRunner(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    @Inject(optional = true)
    public void runTasks(Set<FixedRepeatingAction> actionSet) {
        actionSet.forEach(fixedRepeatingAction -> taskFactory
                .newRepeatingTask(fixedRepeatingAction, fixedRepeatingAction.getInterval())
                .start());
    }
}
