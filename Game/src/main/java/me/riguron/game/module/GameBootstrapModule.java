package me.riguron.game.module;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import me.riguron.game.bootstrap.GameProvider;

@RequiredArgsConstructor
public class GameBootstrapModule extends AbstractModule {

    private final GameProvider gameProvider;

    @Override
    protected void configure() {
        bind(GameProvider.class).toInstance(gameProvider);
    }
}
