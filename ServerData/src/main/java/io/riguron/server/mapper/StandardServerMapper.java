package io.riguron.server.mapper;

import io.riguron.server.type.Server;

import java.util.HashMap;
import java.util.Map;

public class StandardServerMapper implements ServerMapper<Server> {

    @Override
    public Server deserialize(Map<String, String> data) {
        return new Server(
                data.get("name"),
                Boolean.valueOf(data.get("enabled")),
                Integer.valueOf(data.get("onlinePlayers")),
                Integer.valueOf(data.get("maxPlayers"))
        );
    }

    @Override
    public Map<String, String> serialize(Server server) {
        Map<String, String> result = new HashMap<>();
        result.put("name", server.getName());
        result.put("enabled", String.valueOf(server.isEnabled()));
        result.put("onlinePlayers", String.valueOf(server.getOnlinePlayers()));
        result.put("maxPlayers", String.valueOf(server.getMaxPlayers()));
        return result;
    }
}
