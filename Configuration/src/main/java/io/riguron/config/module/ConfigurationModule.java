package io.riguron.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import io.riguron.config.client.ConfigurationLoader;
import io.riguron.config.client.ConfigurationSaver;
import io.riguron.config.factory.ConfigurationProviderFactory;
import io.riguron.config.factory.stream.StreamWriterFactory;
import io.riguron.config.properties.PropertiesFactory;
import io.riguron.io.factory.DataFormat;
import io.riguron.io.factory.stream.FileStreamProviderFactory;
import io.riguron.io.factory.stream.StreamProviderFactory;
import io.riguron.io.list.DirectoryList;
import io.riguron.io.list.FileDirectoryList;

public class ConfigurationModule extends AbstractModule {

    @Provides
    @Singleton
    public PropertiesFactory propertiesFactory(StreamProviderFactory streamProviderFactory) {
        return new PropertiesFactory(streamProviderFactory);
    }

    @Override
    protected void configure() {
        install(new JsonConfigurationModule());
    }

    @ProvidesIntoOptional(value = ProvidesIntoOptional.Type.DEFAULT)
    @Singleton
    protected StreamProviderFactory streamProviderFactory(DataFormat dataFormat) {
        return new FileStreamProviderFactory(dataFormat);
    }

    @Provides
    @Singleton
    public ConfigurationSaver configurationSaver(StreamWriterFactory streamWriterFactory) {
        return new ConfigurationSaver(streamWriterFactory);
    }

    @ProvidesIntoOptional(value = ProvidesIntoOptional.Type.DEFAULT)
    @Singleton
    public DirectoryList directoryList() {
        return new FileDirectoryList();
    }

    @Provides
    @Singleton
    public ConfigurationLoader configurationFactory(ConfigurationProviderFactory configurationProviderFactory, StreamProviderFactory streamProviderFactory, DirectoryList directoryList) {
        return new ConfigurationLoader(configurationProviderFactory, streamProviderFactory, directoryList);
    }
}
