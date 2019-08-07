package me.riguron.config.factory;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import me.riguron.config.json.JsonConfigurationProvider;
import me.riguron.config.provider.ConfigurationProvider;
import me.riguron.io.provider.InputStreamProvider;

@AllArgsConstructor
public class JsonConfigurationFactory implements ConfigurationProviderFactory {

    private Gson gson;

    @Override
    public <T> ConfigurationProvider<T> newConfigurationProvider(Class<T> type, InputStreamProvider streamProvider) {
        return new JsonConfigurationProvider<>(gson, type, streamProvider);
    }
}
