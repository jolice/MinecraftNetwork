package me.riguron.system;

import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.config.properties.PropertiesFactory;
import me.riguron.io.list.DirectoryList;
import me.riguron.persistence.module.PersistenceModule;
import me.riguron.system.chat.ChatModule;
import me.riguron.system.internalization.InternalizationService;
import me.riguron.system.login.LoginProcessingModule;
import me.riguron.system.player.PlayerProfileModule;
import me.riguron.system.rank.RankModule;

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
