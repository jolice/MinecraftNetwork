package me.riguron.bukkit.selector;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import me.riguron.bukkit.selector.factory.SelectorFactory;
import me.riguron.bukkit.selector.factory.SelectorInventoryFactory;
import me.riguron.bukkit.selector.factory.StoringSelectorFactory;
import me.riguron.bukkit.selector.global.GlobalSelectorInterface;
import me.riguron.bukkit.selector.global.GlobalSelectorInventory;
import me.riguron.bukkit.selector.global.GlobalSelectorItemFactory;
import me.riguron.bukkit.selector.global.GlobalSelectorUpdater;
import me.riguron.bukkit.selector.global.config.GlobalSelectorConfig;
import me.riguron.bukkit.server.PlayerServerConnector;
import me.riguron.config.client.ConfigurationLoader;
import me.riguron.server.repository.GlobalOnlineRepository;
import me.riguron.system.task.FixedRepeatingAction;
import me.riguron.system.task.TaskFactory;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class SelectorModule extends AbstractModule {

    @Singleton
    @Provides
    public SelectorFactory<ItemStack> selectorFactory(Plugin plugin, TaskFactory taskFactory, PlayerServerConnector serverConnector, SelectorRepository selectorRepository) {
        return new StoringSelectorFactory<>(selectorRepository,
                new SelectorInventoryFactory(plugin, taskFactory, serverConnector)
        );
    }

    @Singleton
    @Provides
    public SelectorRepository selectorRepository() {
        return new SelectorRepository();
    }

    @ProvidesIntoSet
    @Singleton
    public FixedRepeatingAction globalSelectorUpdater(GlobalOnlineRepository globalOnlineRepository, TaskFactory taskFactory, GlobalSelectorInterface globalSelectorInterface) {
        return new GlobalSelectorUpdater(globalOnlineRepository, globalSelectorInterface, taskFactory);
    }

    @Provides
    @Singleton
    public GlobalSelectorInterface globalSelectorInterface(Plugin plugin, GlobalSelectorItemFactory selectorItemFactory, GlobalSelectorConfig globalSelectorConfig) {
        return new GlobalSelectorInventory(plugin, selectorItemFactory, globalSelectorConfig);
    }

    @Provides
    @Singleton
    public GlobalSelectorConfig globalSelectorConfig(ConfigurationLoader configurationLoader) {
        return configurationLoader.load("selector", GlobalSelectorConfig.class);
    }

    @Provides
    @Singleton
    public GlobalSelectorItemFactory globalSelectorItemFactory(Server server, SelectorRepository selectorRepository) {
        return new GlobalSelectorItemFactory(selectorRepository, server);
    }


}
