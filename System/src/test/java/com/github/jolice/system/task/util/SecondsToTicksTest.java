package com.github.jolice.system.task.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecondsToTicksTest {

    @Test
    public void convert() {
        SecondsToTicks secondsToTicks = new SecondsToTicks(1);
        assertEquals(20, secondsToTicks.convert());
    }
}