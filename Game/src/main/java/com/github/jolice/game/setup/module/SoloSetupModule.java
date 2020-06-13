package com.github.jolice.game.setup.module;

import com.github.jolice.game.setup.MapSetup;
import com.github.jolice.game.setup.SoloMapSetup;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class SoloSetupModule extends AbstractModule {

    @Provides
    @Singleton
    public MapSetup soloMapSetup() {
        return new SoloMapSetup();
    }

}
