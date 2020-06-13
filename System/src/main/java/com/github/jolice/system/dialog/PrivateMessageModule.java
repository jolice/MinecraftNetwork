package com.github.jolice.system.dialog;

import com.github.jolice.system.dialog.ignore.IgnoreData;
import com.github.jolice.system.dialog.ignore.IgnoreRepository;
import com.github.jolice.system.dialog.ignore.IgnoreService;
import com.github.jolice.system.dialog.send.SendModule;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import com.github.jolice.persistence.entity.EntityGroup;

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
