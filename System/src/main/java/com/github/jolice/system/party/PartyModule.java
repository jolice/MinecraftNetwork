package com.github.jolice.system.party;

import com.github.jolice.system.message.PlayerMessaging;
import com.github.jolice.system.preferences.PreferencesRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.server.redis.RedisTransactions;

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
