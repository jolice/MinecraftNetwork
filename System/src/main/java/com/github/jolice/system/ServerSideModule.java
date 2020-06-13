package com.github.jolice.system;

import com.github.jolice.system.chat.ChatModule;
import com.github.jolice.system.internalization.InternalizationService;
import com.github.jolice.system.login.LoginProcessingModule;
import com.github.jolice.system.player.PlayerProfileModule;
import com.github.jolice.system.rank.RankModule;
import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.config.properties.PropertiesFactory;
import com.github.jolice.io.list.DirectoryList;
import com.github.jolice.persistence.module.PersistenceModule;

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
