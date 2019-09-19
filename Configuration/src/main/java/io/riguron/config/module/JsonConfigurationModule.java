package io.riguron.config.module;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.config.factory.stream.StreamWriterFactory;
import io.riguron.config.factory.ConfigurationProviderFactory;
import io.riguron.config.factory.JsonConfigurationFactory;
import io.riguron.config.factory.stream.JsonStreamReaderFactory;
import io.riguron.config.factory.stream.JsonStreamWriterFactory;
import io.riguron.config.factory.stream.StreamReaderFactory;
import io.riguron.io.factory.DataFormat;
import io.riguron.io.factory.stream.StreamProviderFactory;

public class JsonConfigurationModule extends AbstractModule {



    @Provides
    @Singleton
    public StreamReaderFactory streamReaderFactory(Gson gson, StreamProviderFactory streamProviderFactory) {
        return new JsonStreamReaderFactory(gson, streamProviderFactory);
    }

    @Provides
    @Singleton
    public StreamWriterFactory streamWriterFactory(Gson gson, StreamProviderFactory streamProviderFactory) {
        return new JsonStreamWriterFactory(streamProviderFactory, gson);
    }

    @Provides
    @Singleton
    public DataFormat dataFormat() {
        return DataFormat.JSON;
    }

    @Provides
    @Singleton
    public ConfigurationProviderFactory configurationFactory(Gson gson) {
        return new JsonConfigurationFactory(gson);
    }
}
