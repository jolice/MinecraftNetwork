package me.riguron.game.module.game;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.game.listener.state.StateDependentListener;
import me.riguron.game.listener.state.active.ActiveJoinListener;
import me.riguron.game.listener.state.active.PlayerSpectatorListener;
import me.riguron.game.listener.state.active.SpectatorActionListener;
import me.riguron.game.player.spectator.Spectator;
import me.riguron.game.player.repository.GamePlayerStorage;

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
