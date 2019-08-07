package me.riguron.bukkit.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.bukkit.World;

import java.lang.reflect.Type;

/**
 * Gson adapter for serializing World
 */
public class WorldSerializer implements JsonSerializer<World> {

    @Override
    public JsonElement serialize(World world, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", world.getName());
        return jsonObject;
    }

}
