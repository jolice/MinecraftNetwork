package me.riguron.bukkit.plugin;

import com.google.inject.Guice;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import me.riguron.system.plugin.PluginConfiguration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class responsible for registering Guice modules.
 */
@RequiredArgsConstructor
public class PluginBootstrapper {

    private final PluginConfiguration pluginConfiguration;
    private final Plugin plugin;

    public void bootstrap() {
        Guice.createInjector(Stream
                .concat(
                        Stream.of(new PluginModule(plugin, pluginConfiguration)),
                        this.pluginConfiguration.modules().stream()
                ).collect(Collectors.toList())
        );


    }
}
