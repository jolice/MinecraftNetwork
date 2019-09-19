package io.riguron.config.factory;

import com.google.gson.Gson;
import io.riguron.config.json.JsonConfigurationProvider;
import io.riguron.config.provider.ConfigurationProvider;
import lombok.AllArgsConstructor;
import io.riguron.io.provider.InputStreamProvider;

@AllArgsConstructor
public class JsonConfigurationFactory implements ConfigurationProviderFactory {

    private Gson gson;

    @Override
    public <T> ConfigurationProvider<T> newConfigurationProvider(Class<T> type, InputStreamProvider streamProvider) {
        return new JsonConfigurationProvider<>(gson, type, streamProvider);
    }
}
