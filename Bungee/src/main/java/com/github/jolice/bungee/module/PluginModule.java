package io.riguron.bungee.module;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Plugin;
import io.riguron.system.CoreModule;

@RequiredArgsConstructor
public class PluginModule extends AbstractModule {

    private final Plugin plugin;

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        install(new BungeeModule());
        install(new CoreModule());

    }
}


