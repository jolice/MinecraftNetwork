package io.riguron.config.provider;

/**
 * Interface responsible for loading configuration objects from external sources.
 *
 * @param <T> type of de-serialized object
 */
public interface ConfigurationProvider<T> {

    T load();

}
