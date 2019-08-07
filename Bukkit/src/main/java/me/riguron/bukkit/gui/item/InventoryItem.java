package me.riguron.bukkit.gui.item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

/**
 * Represents an item that can be added to an
 * inventory at the certain slot and page.
 * Plain wrapper for ItemStack.
 */
@Data
@Setter(AccessLevel.NONE)
public class InventoryItem {

    private final ItemStack itemStack;
    private final int slot;
    private final int page;

    public InventoryItem(ItemStack item, int slot) {
        this(item, slot, 1);
    }

    public InventoryItem(ItemStack item, int slot, int page) {
        this.itemStack = item;
        this.slot = slot;
        this.page = page;
    }
}
