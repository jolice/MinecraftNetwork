package me.riguron.config.properties;

import lombok.RequiredArgsConstructor;
import me.riguron.io.factory.stream.StreamProviderFactory;

@RequiredArgsConstructor
public class PropertiesFactory {

    private final StreamProviderFactory streamProviderFactory;

    public PropertiesLoader newPropertiesLoader(String path) {
        return new PropertiesLoader(streamProviderFactory.newInputStreamProvider(path));
    }
}
