package com.github.jolice.server.mapper;

import com.github.jolice.server.type.Server;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StandardServerMapperTest {

    @Test
    public void deserialize() {
        Server server = new Server(
                "name",
                true,
                10,
                100
        );
        ServerMapper<Server> mapper  = new StandardServerMapper();
        Map<String, String> serialized = mapper.serialize(server);
        assertEquals(server, mapper.deserialize(serialized));
    }
}