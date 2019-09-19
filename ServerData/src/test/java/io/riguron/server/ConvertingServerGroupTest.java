package io.riguron.server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertingServerGroupTest {

    @Test
    public void whenPartOfGroup() {

        ServerGroup group = new ConvertingServerGroup("bw-1");
        assertEquals("bw", group.getName());
    }

    @Test
    public void whenNotPart() {

        ServerGroup serverGroup = new ConvertingServerGroup("creative");
        assertEquals("creative", serverGroup.getName());
    }
}