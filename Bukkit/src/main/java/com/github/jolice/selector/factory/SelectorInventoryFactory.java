package com.github.jolice.selector.factory;

import com.github.jolice.selector.*;
import com.github.jolice.server.PlayerServerConnector;
import com.github.jolice.selector.iterator.LobbySlotIterator;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import io.riguron.bukkit.selector.*;
import com.github.jolice.selector.gui.DynamicSelectorInventory;
import com.github.jolice.selector.iterator.GameSlotIterator;
import io.riguron.server.type.Server;
import io.riguron.system.server.ServerChannel;
import io.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class SelectorInventoryFactory implements SelectorFactory<ItemStack> {

    private static final int UPDATING_INTERVAL = 1;

    private final Plugin plugin;
    private final TaskFactory taskFactory;
    private final PlayerServerConnector connector;

    @Override
    public <T extends Server> ServerSelector<T> newServerSelector(ServerChannel<T> serverChannel, ServerKind kind, ServerItemProvider<ItemStack, T> itemProvider) {
        ServerConnectionActions serverConnectionActions = new ServerConnectionActions(serverChannel, connector);
        ServerSelector<T> selector = new DynamicSelectorInventory<>(
                plugin,
                () -> kind == ServerKind.GAME ? new GameSlotIterator() : new LobbySlotIterator(),
                serverConnectionActions,
                itemProvider
        );

        DynamicSelectorUpdater<T> updater = new DynamicSelectorUpdater<>(selector, serverChannel);
        taskFactory.newRepeatingTask(() -> taskFactory
                        .newAsyncFunction(updater)
                        .completeSynchronously(updater::update),
                UPDATING_INTERVAL);
        return selector;
    }
}
