package me.riguron.io.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveExtensionTest {

    @Test
    public void apply() {
        RemoveExtension removeExtension = new RemoveExtension();
        String result = removeExtension.apply("str.txt");
        assertEquals("str", result);
    }
}