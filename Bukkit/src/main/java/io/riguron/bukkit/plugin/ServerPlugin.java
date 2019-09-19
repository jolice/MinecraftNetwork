package io.riguron.bukkit.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import io.riguron.system.plugin.PluginConfiguration;

public abstract class ServerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            PluginBootstrapper pluginBootstrapper = new PluginBootstrapper(configuration(), this);
            pluginBootstrapper.bootstrap();
        });
    }

    /**
     * This method is overrode by the concrete plugins to provide the
     * configuration of the custom plugin.
     *
     * @return configuration of custom plugin
     */
    protected abstract PluginConfiguration configuration();

}
