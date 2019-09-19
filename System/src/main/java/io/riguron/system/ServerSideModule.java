package io.riguron.system;

import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.config.properties.PropertiesFactory;
import io.riguron.io.list.DirectoryList;
import io.riguron.persistence.module.PersistenceModule;
import io.riguron.system.chat.ChatModule;
import io.riguron.system.chat.ChatModule;
import io.riguron.system.internalization.InternalizationService;
import io.riguron.system.login.LoginProcessingModule;
import io.riguron.system.player.PlayerProfileModule;
import io.riguron.system.rank.RankModule;

import java.util.Locale;

public class ServerSideModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PersistenceModule());
        install(new LoginProcessingModule());
        install(new PlayerProfileModule());
        install(new RankModule());
        install(new ChatModule());
    }

    @Singleton
    @Provides
    public InternalizationService internalizationService(DirectoryList directoryList, PropertiesFactory propertiesFactory) {
        InternalizationService internalizationService = new InternalizationService();
        directoryList
                .listDirectory("languages")
                .forEach(path -> internalizationService.add(Locale.forLanguageTag(path),
                        () -> Maps.fromProperties(propertiesFactory.newPropertiesLoader(path).load())));
        return internalizationService;
    }


}
