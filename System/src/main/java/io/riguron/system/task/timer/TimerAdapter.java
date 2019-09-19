package io.riguron.system.task.timer;

import io.riguron.system.task.repeating.RepeatingTask;
import lombok.RequiredArgsConstructor;
import io.riguron.system.task.repeating.RepeatingTask;

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
