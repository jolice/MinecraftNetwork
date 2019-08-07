package me.riguron.config.json;

import com.google.gson.Gson;
import me.riguron.config.provider.StreamConfigurationProvider;
import me.riguron.io.provider.InputStreamProvider;

public class JsonConfigurationProvider<T> extends StreamConfigurationProvider<T> {

    public JsonConfigurationProvider(Gson gson, Class<T> type, InputStreamProvider streamProvider) {
        super(new JsonStreamReader<>(gson, type, streamProvider));
    }
}
