package io.riguron.bukkit.plugin;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import io.riguron.bukkit.BukkitModule;
import io.riguron.command.module.CommandModule;
import io.riguron.persistence.entity.EntityGroup;
import io.riguron.system.CoreModule;
import io.riguron.system.ServerSideModule;
import io.riguron.system.plugin.PluginConfiguration;

@RequiredArgsConstructor
public class PluginModule extends AbstractModule {

    private final Plugin plugin;
    private final PluginConfiguration pluginConfiguration;

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        install(new CoreModule());
        install(new CommandModule());
        install(new BukkitModule());
        install(new ServerSideModule());
    }

    @Singleton
    @Provides
    public Server server(Plugin plugin) {
        return plugin.getServer();
    }

    @Singleton
    @Provides
    public PluginManager pluginManager(Server server) {
        return server.getPluginManager();
    }

    @Singleton
    @Provides
    @Named("CustomEntities")
    public EntityGroup entities() {
        return new EntityGroup(pluginConfiguration.entities());
    }

}
