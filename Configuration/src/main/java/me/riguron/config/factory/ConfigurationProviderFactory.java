package me.riguron.config.factory;

import me.riguron.config.provider.ConfigurationProvider;
import me.riguron.io.provider.InputStreamProvider;

/**
 * Class responsible for creating ConfigurationProvider instances.
 * High-level component.
 */
public interface ConfigurationProviderFactory {

    /**
     * Creates new configuration provider.
     *
     * @param type           type of the serialized object
     * @param streamProvider external resource input stream provider
     * @param <T>            type of the serialized object
     * @return configuration provider instance
     */
    <T> ConfigurationProvider<T> newConfigurationProvider(Class<T> type, InputStreamProvider streamProvider);
}
