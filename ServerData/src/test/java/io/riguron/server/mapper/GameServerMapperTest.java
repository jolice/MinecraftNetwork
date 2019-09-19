package io.riguron.server.mapper;

import io.riguron.server.type.Server;
import io.riguron.server.type.GameServer;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GameServerMapperTest {

    @Test
    public void deserialize() {

        GameServerMapper gameServerMapper = new GameServerMapper(
                new StandardServerMapper()
        );
        Server server = new Server("name", true, 10, 100);

        GameServer gameServer = new GameServer(
                server,
                "Map",
                "GameName",
                true
        );

        Map<String, String> serialize = gameServerMapper.serialize(gameServer);
        GameServer deSerialized = gameServerMapper.deserialize(serialize);
        assertEquals(deSerialized, gameServer);
    }
}