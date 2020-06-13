package com.github.jolice.system;

import com.github.jolice.system.message.PlayerMessaging;
import com.github.jolice.system.party.PartyModule;
import com.github.jolice.system.server.ServerChannelFactory;
import com.github.jolice.system.task.FixedRepeatingTaskRunner;
import com.github.jolice.system.task.startup.PostLoadTaskRunner;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.common.CommonModule;
import com.github.jolice.config.module.ConfigurationModule;
import com.github.jolice.messaging.MessagingModule;
import com.github.jolice.messaging.MessagingService;
import com.github.jolice.server.ServerDataModule;
import com.github.jolice.server.repository.OnlineIndexingRepository;
import com.github.jolice.server.repository.PlayerRepository;
import com.github.jolice.server.repository.ServerRepository;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(FixedRepeatingTaskRunner.class).asEagerSingleton();
        bind(PostLoadTaskRunner.class).asEagerSingleton();
        install(new ConfigurationModule());
        install(new CommonModule());
        install(new ServerDataModule());
        install(new MessagingModule());
        install(new PartyModule());
    }

    @Provides
    @Singleton
    public ServerChannelFactory serverChannelFactory(ServerRepository repository, OnlineIndexingRepository onlineIndexingRepository) {
        return new ServerChannelFactory(onlineIndexingRepository, repository);
    }


    @Provides
    @Singleton
    public PlayerMessaging playerMessaging(MessagingService messagingService, PlayerRepository repository) {
        return new PlayerMessaging(messagingService, repository);
    }

}
