package io.riguron.game.module;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import io.riguron.game.bootstrap.GameProvider;

@RequiredArgsConstructor
public class GameBootstrapModule extends AbstractModule {

    private final GameProvider gameProvider;

    @Override
    protected void configure() {
        bind(GameProvider.class).toInstance(gameProvider);
    }
}
