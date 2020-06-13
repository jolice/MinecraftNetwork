package com.github.jolice.game.setup.module;

import com.github.jolice.game.setup.MapSetup;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.game.setup.TeamMapSetup;

public class TeamSetupModule extends AbstractModule {

    @Provides
    @Singleton
    public MapSetup teamMapSetup() {
        return new TeamMapSetup();
    }
}
