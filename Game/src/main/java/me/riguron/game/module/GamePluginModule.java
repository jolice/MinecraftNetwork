package me.riguron.game.module;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import me.riguron.game.bootstrap.GameProvider;
import me.riguron.game.core.GameKind;
import me.riguron.game.kit.KitModule;
import me.riguron.game.module.game.ActiveModule;
import me.riguron.game.module.game.StartingModule;
import me.riguron.game.module.game.WaitingModule;
import me.riguron.game.module.team.TeamModule;
import me.riguron.game.server.GameServerDataModule;

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
