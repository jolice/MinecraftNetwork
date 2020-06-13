package com.github.jolice.system.chat;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.system.player.PlayerProfileRepository;

public class ChatModule extends AbstractModule {

    @Provides
    @Singleton
    public ChatFormatter chatFormatter(PlayerProfileRepository repository) {
        return new ChatFormatter(repository);
    }

    @Provides
    @Singleton
    public ChatCooldowns cooldowns() {
        return new ChatCooldowns(3);
    }
}
