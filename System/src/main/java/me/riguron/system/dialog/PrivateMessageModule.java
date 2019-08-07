package me.riguron.system.dialog;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import me.riguron.persistence.entity.EntityGroup;
import me.riguron.system.dialog.ignore.IgnoreData;
import me.riguron.system.dialog.ignore.IgnoreRepository;
import me.riguron.system.dialog.ignore.IgnoreService;
import me.riguron.system.dialog.send.SendModule;
import me.riguron.system.player.PlayerProfileRepository;

import java.util.Arrays;

public class PrivateMessageModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new SendModule());
    }

    @Singleton
    @Provides
    public PrivateMessageRepository privateMessageRepository(EbeanServer ebeanServer) {
        return new PrivateMessageRepository(ebeanServer);
    }

    @Singleton
    @ProvidesIntoSet
    public EntityGroup entityGroup() {
        return new EntityGroup(Arrays.asList(PrivateMessageRecord.class, IgnoreData.class));
    }

    @Singleton
    @Provides
    public IgnoreRepository ignoreRepository(EbeanServer ebeanServer) {
        return new IgnoreRepository(ebeanServer);
    }

    @Singleton
    @Provides
    public IgnoreService ignoreService(PlayerProfileRepository repository, IgnoreRepository ignoreRepository) {
        return new IgnoreService(repository, ignoreRepository);
    }

}
