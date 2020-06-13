package com.github.jolice.system.task.timer;

import com.github.jolice.system.task.repeating.RepeatingTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimerAdapter implements Timer {

    private final RepeatingTask delegate;

    @Override
    public void start() {
        delegate.start();
    }

    @Override
    public void stop() {
        delegate.stop();
    }
}
