package me.riguron.config.client;

import lombok.RequiredArgsConstructor;
import me.riguron.config.Configuration;
import me.riguron.config.factory.stream.StreamWriterFactory;

/**
 * Client class for writing configuration objects to external resources.
 */
@RequiredArgsConstructor
public class ConfigurationSaver {

    private final StreamWriterFactory streamWriterFactory;

    public void save(Configuration configuration, String name) {
        streamWriterFactory.newStreamWriter(name).write(configuration);
    }

}
