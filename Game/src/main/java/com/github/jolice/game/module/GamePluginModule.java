package com.github.jolice.game.module;

import com.github.jolice.game.bootstrap.GameProvider;
import com.github.jolice.game.core.GameKind;
import com.github.jolice.game.kit.KitModule;
import com.github.jolice.game.module.game.ActiveModule;
import com.github.jolice.game.module.game.StartingModule;
import com.github.jolice.game.module.game.WaitingModule;
import com.github.jolice.game.module.team.TeamModule;
import com.github.jolice.game.server.GameServerDataModule;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GamePluginModule extends AbstractModule {

    private final GameProvider gameProvider;

    @Override
    protected void configure() {
        install(new GeneralModule());
        install(new WaitingModule());
        install(new StartingModule());
        install(new GameServerDataModule());
        install(new ActiveModule());
        install(new ListenerModule());
        install(new GameModule());
        install(new KitModule());
        install(new GameBootstrapModule(gameProvider));
        install(gameProvider.getKind().equals(GameKind.TEAM) ? new TeamModule() : new SoloModule());
    }
}
