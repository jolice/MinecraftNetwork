package io.riguron.config.client;

import lombok.RequiredArgsConstructor;
import io.riguron.config.factory.ConfigurationProviderFactory;
import io.riguron.io.factory.stream.StreamProviderFactory;
import io.riguron.io.list.DirectoryList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Client class responsible for loading configurations from external resources
 * using lower level instruments.
 */
@RequiredArgsConstructor
public class ConfigurationLoader {

    private final ConfigurationProviderFactory configurationProviderFactory;
    private final StreamProviderFactory streamProviderFactory;
    private final DirectoryList directoryList;

    public <T> T load(String target, Class<T> type) {
        return configurationProviderFactory.newConfigurationProvider(type, streamProviderFactory.newInputStreamProvider(target)).load();
    }

    public <T> List<T> loadAll(String directory, Class<T> type) {
        return directoryList.listDirectory(directory).stream().map(x -> load(x, type)).collect(Collectors.toList());
    }
}
