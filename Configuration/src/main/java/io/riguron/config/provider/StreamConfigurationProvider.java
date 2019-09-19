package io.riguron.config.provider;

import io.riguron.io.stream.StreamReader;

public class StreamConfigurationProvider<T> implements ConfigurationProvider<T> {

    private StreamReader<T> streamReader;

    public StreamConfigurationProvider(StreamReader<T> streamReader) {
        this.streamReader = streamReader;
    }

    @Override
    public T load() {
        return this.streamReader.read();
    }

}
