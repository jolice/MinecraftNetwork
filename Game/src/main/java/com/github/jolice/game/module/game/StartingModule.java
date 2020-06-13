package com.github.jolice.game.module.game;

import com.github.jolice.game.listener.state.StateDependentListener;
import com.github.jolice.game.listener.state.starting.StartingBlockingListener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.github.jolice.game.config.advanced.AdvancedGameOptions;

public class StartingModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener startingListener(AdvancedGameOptions advancedGameOptions) {
        return new StartingBlockingListener(advancedGameOptions);
    }
}
