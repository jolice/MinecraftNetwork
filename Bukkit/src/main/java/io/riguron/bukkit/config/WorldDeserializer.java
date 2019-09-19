package io.riguron.bukkit.config;

import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.World;

import java.lang.reflect.Type;

/**
 * Gson adapter for de-serializing World
 */
@RequiredArgsConstructor
public class WorldDeserializer implements JsonDeserializer<World> {

    private final Server server;

    @Override
    public World deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return server.getWorld(jsonElement.getAsJsonObject().get("name").getAsString());
    }
}
