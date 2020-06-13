package io.riguron.config.client;

import io.riguron.config.factory.stream.StreamWriterFactory;
import lombok.RequiredArgsConstructor;
import io.riguron.config.Configuration;

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
