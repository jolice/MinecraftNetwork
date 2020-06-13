package com.github.jolice.game.plugin.module;

import com.github.jolice.game.bootstrap.GameProvider;
import com.google.inject.Module;
import com.github.jolice.game.module.GamePluginModule;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import io.riguron.system.plugin.PluginConfiguration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@RequiredArgsConstructor
public class GameModuleConfiguration implements ModuleConfiguration {

    private final GameProvider gameProvider;
    private final PluginConfiguration delegate;

    @Override
    public List<Module> getModules() {
        return Stream.concat(
                Stream.of(new GamePluginModule(gameProvider)),
                delegate.modules().stream()
        ).collect(Collectors.toList());
    }


}
