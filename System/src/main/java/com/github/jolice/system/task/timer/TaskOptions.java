package com.github.jolice.system.task.timer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

@Getter
@EqualsAndHashCode
@ToString
public class TaskOptions {

    private final TimeUnit timeUnit;
    private final int interval;
    private final long timesToRun;

    public TaskOptions(int timesToRun) {
        this(TimeUnit.SECONDS, timesToRun);
    }

    private TaskOptions(TimeUnit timeUnit, int timesToRun) {
        this(timeUnit, 1, timesToRun);
    }

    private TaskOptions(TimeUnit timeUnit, int interval, long timesToRun) {
        this.timeUnit = timeUnit;
        this.interval = interval;
        this.timesToRun = timesToRun;
    }
}
