package io.riguron.bukkit.gui.validate;

import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.GUI;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.GUI;
import io.riguron.bukkit.gui.item.InventoryItem;

/**
 * Validating decorator for single-page inventory
 */
@RequiredArgsConstructor
public class SinglePageValidatingGui implements GUI {

    private final GUI gui;

    @Override
    public void displayTo(Player player) {
        gui.displayTo(player);
    }

    @Override
    public void clear() {
        gui.clear();
    }

    @Override
    public void addButton(InventoryItem inventoryItem, Executable executable) {
        assertPageIsOne(inventoryItem);
        gui.addButton(inventoryItem, executable);
    }

    @Override
    public void addItem(InventoryItem inventoryItem) {
        assertPageIsOne(inventoryItem);
        gui.addItem(inventoryItem);
    }

    @Override
    public void removeItem(int slot, int page) {
        assertPageIsOne(page);
        gui.removeItem(slot, page);
    }

    private void assertPageIsOne(InventoryItem inventoryItem) {
        assertPageIsOne(inventoryItem.getPage());
    }

    private void assertPageIsOne(int page) {
        if (page != 1) {
            throw new IllegalArgumentException("Single page inventory accepts only items with page 1");
        }
    }
}

