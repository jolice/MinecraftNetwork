package me.riguron.system.player;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.ebean.EbeanServer;
import me.riguron.persistence.entity.EntityGroup;
import me.riguron.system.preferences.PlayerPreferences;
import me.riguron.system.preferences.PreferencesRepository;
import me.riguron.system.punishment.repository.PunishmentDataRepository;
import me.riguron.system.task.TaskFactory;

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
