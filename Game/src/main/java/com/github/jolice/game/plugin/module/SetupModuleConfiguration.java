package com.github.jolice.game.plugin.module;

import com.github.jolice.game.core.GameKind;
import com.google.inject.Module;
import com.github.jolice.game.setup.module.SetupModule;
import com.github.jolice.game.setup.module.SoloSetupModule;
import com.github.jolice.game.setup.module.TeamSetupModule;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
