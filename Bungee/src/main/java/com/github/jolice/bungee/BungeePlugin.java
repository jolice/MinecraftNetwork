package io.riguron.bungee;

import com.google.inject.Guice;
import io.riguron.bungee.event.PluginDisableEvent;
import io.riguron.bungee.module.PluginModule;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlugin extends Plugin {

    @Override
    public void onEnable() {
        Guice.createInjector(new PluginModule(this));
    }

    @Override
    public void onDisable() {
        getProxy().getPluginManager().callEvent(new PluginDisableEvent());
    }
}
