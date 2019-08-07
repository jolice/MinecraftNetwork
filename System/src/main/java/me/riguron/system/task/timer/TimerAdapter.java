package me.riguron.system.task.timer;

import lombok.RequiredArgsConstructor;
import me.riguron.system.task.repeating.RepeatingTask;

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
