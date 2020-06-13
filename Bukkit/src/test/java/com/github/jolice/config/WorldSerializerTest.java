package com.github.jolice.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.World;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorldSerializerTest {

    @Test
    public void serialize() {

        final String expectedString = "{\"world\":{\"name\":\"main\"},\"x\":0,\"y\":0}";
        World world = mock(World.class);
        when(world.getName()).thenReturn("main");
        TestClass testClass = new TestClass(world);

        Gson gson = new GsonBuilder().registerTypeAdapter(World.class, new WorldSerializer()).create();

        assertEquals(expectedString, gson.toJson(testClass));
    }

    static class TestClass {

        private World world;
        private int x;
        private int y;

        TestClass(World world) {
            this.world = world;
        }

    }
}