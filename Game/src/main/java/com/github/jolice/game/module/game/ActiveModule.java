package com.github.jolice.game.module.game;

import com.github.jolice.game.listener.state.StateDependentListener;
import com.github.jolice.game.listener.state.active.ActiveJoinListener;
import com.github.jolice.game.listener.state.active.PlayerSpectatorListener;
import com.github.jolice.game.listener.state.active.SpectatorActionListener;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.spectator.Spectator;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;

public class ActiveModule extends AbstractModule {


    @ProvidesIntoSet
    @Singleton
    public StateDependentListener activeJoinListener(Spectator spectator) {
        return new ActiveJoinListener(spectator);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener spectatorListener(GamePlayerStorage<?> gamePlayerStorage) {
        return new SpectatorActionListener(gamePlayerStorage);
    }

    @ProvidesIntoSet
    @Singleton
    public PlayerSpectatorListener spectatorSetListener(Spectator spectator) {
        return new PlayerSpectatorListener(spectator);
    }
}
