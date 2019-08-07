package me.riguron.bukkit.gui.single;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import me.riguron.bukkit.gui.Executable;
import me.riguron.bukkit.gui.GUI;
import me.riguron.bukkit.gui.item.InventoryItem;

/**
 * Represents a GUI with one page only. Switching pages is not possible,
 * adding and removing items at page that differs from one has no effect,
 * all pages are treated equally.
 */
public class SinglePageInterface implements GUI, Listener {

    private static final int INVENTORY_SIZE = 54;

    private Inventory inventory;
    private SinglePageListener singlePageListener;

    public SinglePageInterface(Plugin plugin, String title) {
        this.inventory = plugin.getServer().createInventory(null, INVENTORY_SIZE, title);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.singlePageListener = new SinglePageListener();
    }

    @Override
    public void displayTo(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public void addButton(InventoryItem inventoryItem, Executable executable) {
        singlePageListener.addHandler(inventoryItem.getSlot(), executable);
        this.addItem(inventoryItem);
    }

    @Override
    public void addItem(InventoryItem inventoryItem) {
        this.inventory.setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
    }

    @Override
    public void removeItem(int slot, int page) {
        this.inventory.removeItem(this.inventory.getItem(slot));
    }

    @EventHandler
    private void inventoryClicked(InventoryClickEvent e) {
        if (this.inventory.hashCode() == e.getInventory().hashCode()) {
            singlePageListener.inventoryClicked(e);
        }
    }

}
