package com.github.jolice.game.plugin;

import com.github.jolice.game.bootstrap.GameProvider;
import com.github.jolice.game.plugin.module.GameModuleConfiguration;
import com.github.jolice.game.plugin.module.ModuleConfiguration;
import com.github.jolice.game.plugin.module.SetupModuleConfiguration;
import com.google.inject.Module;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.riguron.system.plugin.PluginConfiguration;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class GamePluginConfiguration implements PluginConfiguration {

    private static final String SETUP = "setup";

    private final GameProvider gameProvider;
    private final PluginConfiguration delegate;

    @Override
    public List<Module> modules() {
        ModuleConfiguration moduleConfiguration = isSetup() ? new SetupModuleConfiguration(gameProvider.getKind()) : new GameModuleConfiguration(gameProvider, delegate);
        log.info("Using {} module configuration", moduleConfiguration);
        return moduleConfiguration.getModules();
    }

    @Override
    public List<Class<?>> entities() {
        return delegate.entities();
    }

    private boolean isSetup() {
        return SETUP.equals(System.getProperty("game.mode"));
    }

}
