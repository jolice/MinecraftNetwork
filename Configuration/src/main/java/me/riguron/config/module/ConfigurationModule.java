package me.riguron.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import me.riguron.config.client.ConfigurationLoader;
import me.riguron.config.client.ConfigurationSaver;
import me.riguron.config.factory.ConfigurationProviderFactory;
import me.riguron.config.factory.stream.StreamWriterFactory;
import me.riguron.config.properties.PropertiesFactory;
import me.riguron.io.factory.DataFormat;
import me.riguron.io.factory.stream.FileStreamProviderFactory;
import me.riguron.io.factory.stream.StreamProviderFactory;
import me.riguron.io.list.DirectoryList;
import me.riguron.io.list.FileDirectoryList;

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
