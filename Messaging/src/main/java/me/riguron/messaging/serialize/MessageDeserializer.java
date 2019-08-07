package me.riguron.messaging.serialize;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class MessageDeserializer {

    private final Gson gson;

    public <T> T deserialize(String json, Type type) {
        return gson.fromJson(json, type);
    }

}
