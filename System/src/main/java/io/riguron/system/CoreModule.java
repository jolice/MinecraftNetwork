package io.riguron.system;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.common.CommonModule;
import io.riguron.config.module.ConfigurationModule;
import io.riguron.messaging.MessagingModule;
import io.riguron.messaging.MessagingService;
import io.riguron.server.ServerDataModule;
import io.riguron.server.repository.OnlineIndexingRepository;
import io.riguron.server.repository.PlayerRepository;
import io.riguron.server.repository.ServerRepository;
import io.riguron.system.message.PlayerMessaging;
import io.riguron.system.party.PartyModule;
import io.riguron.system.server.ServerChannelFactory;
import io.riguron.system.task.FixedRepeatingTaskRunner;
import io.riguron.system.task.startup.PostLoadTaskRunner;

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
