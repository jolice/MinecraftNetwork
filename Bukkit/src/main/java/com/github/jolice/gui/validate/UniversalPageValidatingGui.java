package com.github.jolice.gui.validate;

import com.github.jolice.gui.Executable;
import com.github.jolice.gui.GUI;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import com.github.jolice.gui.item.InventoryItem;

/**
 * Page-validating decorator for both single and multi page GUIs
 */
@RequiredArgsConstructor
public class UniversalPageValidatingGui implements GUI {

    private final GUI delegate;

    @Override
    public void displayTo(Player player) {
        delegate.displayTo(player);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public void addButton(InventoryItem inventoryItem, Executable executable) {
        validatePage(inventoryItem);
        delegate.addItem(inventoryItem);
    }

    @Override
    public void addItem(InventoryItem inventoryItem) {
        validatePage(inventoryItem);
        delegate.addItem(inventoryItem);
    }

    @Override
    public void removeItem(int slot, int page) {
        validatePage(page);
        delegate.removeItem(slot, page);
    }

    private void validatePage(int page) {
        if (page < 1) {
            throw new IllegalArgumentException("Page must be greater than zero");
        }
    }

    private void validatePage(InventoryItem inventoryItem) {
        validatePage(inventoryItem.getPage());
    }
}
