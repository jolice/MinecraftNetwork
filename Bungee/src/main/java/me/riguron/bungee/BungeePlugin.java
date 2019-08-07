package me.riguron.bungee;

import com.google.inject.Guice;
import net.md_5.bungee.api.plugin.Plugin;
import me.riguron.bungee.event.PluginDisableEvent;
import me.riguron.bungee.module.PluginModule;

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
