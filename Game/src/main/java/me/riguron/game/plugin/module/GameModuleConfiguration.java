package me.riguron.game.plugin.module;

import com.google.inject.Module;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.riguron.game.bootstrap.GameProvider;
import me.riguron.game.module.GamePluginModule;
import me.riguron.system.plugin.PluginConfiguration;

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
