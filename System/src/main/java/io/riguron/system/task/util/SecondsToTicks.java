package io.riguron.system.task.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecondsToTicks {

    private static final long TICKS_PER_SECOND = 20L;

    private final int seconds;

    public long convert() {
        return seconds * TICKS_PER_SECOND;
    }
}
