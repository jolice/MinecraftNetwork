package com.github.jolice.game.module;

import com.github.jolice.game.bootstrap.GameProvider;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameBootstrapModule extends AbstractModule {

    private final GameProvider gameProvider;

    @Override
    protected void configure() {
        bind(GameProvider.class).toInstance(gameProvider);
    }
}
