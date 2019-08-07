package me.riguron.config.module;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.config.factory.ConfigurationProviderFactory;
import me.riguron.config.factory.JsonConfigurationFactory;
import me.riguron.config.factory.stream.JsonStreamReaderFactory;
import me.riguron.config.factory.stream.JsonStreamWriterFactory;
import me.riguron.config.factory.stream.StreamReaderFactory;
import me.riguron.config.factory.stream.StreamWriterFactory;
import me.riguron.io.factory.DataFormat;
import me.riguron.io.factory.stream.StreamProviderFactory;

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
