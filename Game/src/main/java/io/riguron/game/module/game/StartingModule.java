package io.riguron.game.module.game;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.game.config.advanced.AdvancedGameOptions;
import io.riguron.game.listener.state.StateDependentListener;
import io.riguron.game.listener.state.starting.StartingBlockingListener;

public class StartingModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener startingListener(AdvancedGameOptions advancedGameOptions) {
        return new StartingBlockingListener(advancedGameOptions);
    }
}
