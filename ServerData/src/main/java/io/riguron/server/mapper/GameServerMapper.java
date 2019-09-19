package io.riguron.server.mapper;

import io.riguron.server.type.GameServer;
import io.riguron.server.type.Server;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GameServerMapper implements ServerMapper<GameServer> {

    private final ServerMapper<Server> delegateMapper;

    @Override
    public GameServer deserialize(Map<String, String> data) {
        return new GameServer(
                delegateMapper.deserialize(data),
                data.get("map"),
                data.get("gameName"),
                Boolean.valueOf(data.get("active"))
        );
    }

    @Override
    public Map<String, String> serialize(GameServer server) {
        Map<String, String> result = new HashMap<>(delegateMapper.serialize(server));
        result.put("map", server.getMap());
        result.put("gameName", server.getGameName());
        result.put("active", Boolean.toString(server.isActive()));
        return result;
    }
}
