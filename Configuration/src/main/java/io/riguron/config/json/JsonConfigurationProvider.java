package io.riguron.config.json;

import com.google.gson.Gson;
import io.riguron.config.provider.StreamConfigurationProvider;
import io.riguron.io.provider.InputStreamProvider;

public class JsonConfigurationProvider<T> extends StreamConfigurationProvider<T> {

    public JsonConfigurationProvider(Gson gson, Class<T> type, InputStreamProvider streamProvider) {
        super(new JsonStreamReader<>(gson, type, streamProvider));
    }
}
