package io.riguron.game.module;

import com.google.inject.AbstractModule;
import io.riguron.game.module.game.ActiveModule;
import lombok.RequiredArgsConstructor;
import io.riguron.game.bootstrap.GameProvider;
import io.riguron.game.core.GameKind;
import io.riguron.game.kit.KitModule;
import io.riguron.game.module.game.ActiveModule;
import io.riguron.game.module.game.StartingModule;
import io.riguron.game.module.game.WaitingModule;
import io.riguron.game.module.team.TeamModule;
import io.riguron.game.server.GameServerDataModule;

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
