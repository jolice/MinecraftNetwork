package com.github.jolice.system.player;

import com.github.jolice.system.preferences.PlayerPreferences;
import com.github.jolice.system.preferences.PreferencesRepository;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import com.github.jolice.persistence.entity.EntityGroup;
import com.github.jolice.system.task.TaskFactory;

import java.util.Arrays;

public class PlayerProfileModule extends AbstractModule {

    @Provides
    @Singleton
    public PlayerProfileRepository playerRepository(EbeanServer server) {
        return new CachingPlayerRepository(server);
    }

    @ProvidesIntoSet
    @Singleton
    public EntityGroup entityGroup() {
        return new EntityGroup(Arrays.asList(PlayerProfile.class, PlayerPreferences.class));
    }

    @Provides
    @Singleton
    public PlayerLeaveHandler playerLeaveHandler(PunishmentDataRepository punishmentDataRepository, PlayerProfileRepository playerProfileRepository, TaskFactory taskFactory) {
        return new PlayerLeaveHandler(punishmentDataRepository, playerProfileRepository, taskFactory);
    }

    @Provides
    @Singleton
    public PreferencesRepository preferencesRepository(EbeanServer ebeanServer) {
        return new PreferencesRepository(ebeanServer);
    }
}
