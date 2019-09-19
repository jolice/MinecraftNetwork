package io.riguron.system.party;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.server.redis.RedisTransactions;
import io.riguron.system.message.PlayerMessaging;
import io.riguron.system.preferences.PreferencesRepository;

public class PartyModule extends AbstractModule {

    @Provides
    @Singleton
    public PartyRepository partyRepository(RedisTransactions redisTransactions) {
        return new SelfCheckingPartyRepository(new RedisPartyRepository(redisTransactions));
    }

    @Provides
    @Singleton
    public PartyService partyService(PartyRepository partyRepository, PreferencesRepository preferencesRepository, PlayerMessaging playerMessaging) {
        return new PartyService(partyRepository, preferencesRepository, playerMessaging);
    }



}
