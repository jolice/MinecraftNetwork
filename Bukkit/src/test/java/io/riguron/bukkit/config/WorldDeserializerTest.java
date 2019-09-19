package io.riguron.bukkit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bukkit.Server;
import org.bukkit.World;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorldDeserializerTest {

    @Test
    public void deserialize() {
        Server server = mock(Server.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(World.class, new WorldDeserializer(
                server
        ))
                .create();

        World world = mock(World.class);

        when(world.getName()).thenReturn("main");
        when(server.getWorld(eq("main"))).thenReturn(world);

        final TestClass testClass = gson.fromJson(
                "{\"world\":{\"name\":\"main\"},\"x\":0,\"y\":0}",
                TestClass.class
        );
        assertNotNull(
                testClass.world);
        assertEquals("main", testClass.world.getName());


    }

    static class TestClass {

        private World world;
        private int x;
        private int y;

        public TestClass(World world) {
            this.world = world;
        }

    }

}