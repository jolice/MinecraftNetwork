package me.riguron.system;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.common.CommonModule;
import me.riguron.config.module.ConfigurationModule;
import me.riguron.messaging.MessagingModule;
import me.riguron.messaging.MessagingService;
import me.riguron.server.ServerDataModule;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.server.repository.PlayerRepository;
import me.riguron.server.repository.ServerRepository;
import me.riguron.system.message.PlayerMessaging;
import me.riguron.system.party.PartyModule;
import me.riguron.system.server.ServerChannelFactory;
import me.riguron.system.task.FixedRepeatingTaskRunner;
import me.riguron.system.task.startup.PostLoadTaskRunner;

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
