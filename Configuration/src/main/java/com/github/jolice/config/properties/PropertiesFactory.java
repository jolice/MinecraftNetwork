package io.riguron.config.properties;

import lombok.RequiredArgsConstructor;
import io.riguron.io.factory.stream.StreamProviderFactory;

@RequiredArgsConstructor
public class PropertiesFactory {

    private final StreamProviderFactory streamProviderFactory;

    public PropertiesLoader newPropertiesLoader(String path) {
        return new PropertiesLoader(streamProviderFactory.newInputStreamProvider(path));
    }
}
