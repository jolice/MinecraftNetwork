package me.riguron.bukkit.selector.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import me.riguron.bukkit.gui.GUI;
import me.riguron.bukkit.gui.item.InventoryItem;
import me.riguron.bukkit.gui.single.SinglePageInterface;
import me.riguron.bukkit.selector.ServerConnectionActions;
import me.riguron.bukkit.selector.ServerItemProvider;
import me.riguron.bukkit.selector.ServerSelector;
import me.riguron.server.type.Server;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class DynamicSelectorInventory<T extends Server> implements ServerSelector<T> {

    private static final String SERVER_SELECTOR_TITLE = "Select selector";

    private static final int FAST_SERVER_SLOT = 22;

    private final GUI gui;
    private final Supplier<Iterator<Integer>> slotIteratorProvider;
    private final ServerConnectionActions interfaceFactory;
    private final ServerItemProvider<ItemStack, T> itemProvider;

    public DynamicSelectorInventory(Plugin plugin, Supplier<Iterator<Integer>> slotIteratorProvider, ServerConnectionActions interfaceFactory, ServerItemProvider<ItemStack, T> itemProvider) {
        this.gui = new SinglePageInterface(plugin, SERVER_SELECTOR_TITLE);
        this.slotIteratorProvider = slotIteratorProvider;
        this.interfaceFactory = interfaceFactory;
        this.itemProvider = itemProvider;
    }

    @Override
    public void refresh(List<T> servers) {
        gui.clear();
        gui.addButton(new InventoryItem(FastServerItem.ICON, FAST_SERVER_SLOT), interfaceFactory.getFastServerItem());
        Iterator<Integer> cellIterator = slotIteratorProvider.get();
        Iterator<T> gameServerIterator = servers.iterator();
        while (cellIterator.hasNext()) {
            if (gameServerIterator.hasNext()) {
                T server = gameServerIterator.next();
                gui.addButton(
                        new InventoryItem(itemProvider.createServerItem(server), cellIterator.next()),
                        interfaceFactory.getServerConnectItem(server.getName()));
            } else {
                break;
            }
        }
    }

    @Override
    public void displayTo(Player player) {
        gui.displayTo(player);
    }
}
    
