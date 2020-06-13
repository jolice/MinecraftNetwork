package com.github.jolice.selector;

import com.github.jolice.selector.factory.SelectorFactory;
import com.github.jolice.selector.factory.SelectorInventoryFactory;
import com.github.jolice.selector.factory.StoringSelectorFactory;
import com.github.jolice.selector.global.GlobalSelectorInterface;
import com.github.jolice.selector.global.GlobalSelectorInventory;
import com.github.jolice.selector.global.GlobalSelectorItemFactory;
import com.github.jolice.selector.global.GlobalSelectorUpdater;
import com.github.jolice.selector.global.config.GlobalSelectorConfig;
import com.github.jolice.server.PlayerServerConnector;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.riguron.config.client.ConfigurationLoader;
import io.riguron.server.repository.GlobalOnlineRepository;
import io.riguron.system.task.FixedRepeatingAction;
import io.riguron.system.task.TaskFactory;
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
