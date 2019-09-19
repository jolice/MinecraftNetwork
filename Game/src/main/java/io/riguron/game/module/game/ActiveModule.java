package io.riguron.game.module.game;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.game.listener.state.StateDependentListener;
import io.riguron.game.listener.state.active.ActiveJoinListener;
import io.riguron.game.listener.state.active.PlayerSpectatorListener;
import io.riguron.game.listener.state.active.SpectatorActionListener;
import io.riguron.game.player.spectator.Spectator;
import io.riguron.game.player.repository.GamePlayerStorage;

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
