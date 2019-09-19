package io.riguron.game.plugin.module;

import com.google.inject.Module;
import io.riguron.game.core.GameKind;
import io.riguron.game.setup.module.SetupModule;
import io.riguron.game.setup.module.SoloSetupModule;
import io.riguron.game.setup.module.TeamSetupModule;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import io.riguron.game.core.GameKind;
import io.riguron.game.setup.module.SetupModule;
import io.riguron.game.setup.module.SoloSetupModule;
import io.riguron.game.setup.module.TeamSetupModule;

import java.util.ArrayList;
import java.util.List;

@ToString
@RequiredArgsConstructor
public class SetupModuleConfiguration implements ModuleConfiguration {

    private final GameKind gameKind;

    @Override
    public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        if (gameKind == GameKind.SOLO) {
            modules.add(new SoloSetupModule());
        } else {
            modules.add(new TeamSetupModule());
        }
        modules.add(new SetupModule());
        return modules;
    }


}
