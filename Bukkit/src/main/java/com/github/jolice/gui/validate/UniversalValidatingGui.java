package com.github.jolice.gui.validate;

import com.github.jolice.gui.Executable;
import com.github.jolice.gui.GUI;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import com.github.jolice.gui.item.InventoryItem;

/**
 * Validating decorator for both single and multi page GUIs
 */
@RequiredArgsConstructor
public class UniversalValidatingGui implements GUI {

    private static final int MAX_INVENTORY_SIZE = 54;

    private final GUI delegate;

    @Override
    public void displayTo(Player player) {
        assertArgument(player.isOnline(), "Can't display inventory to offline player");
        assertNotNull(player, "Can't display GUI to null player");
        delegate.displayTo(player);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public void addButton(InventoryItem inventoryItem, Executable executable) {
        assertNotNull(inventoryItem, "InventoryItem can not be null");
        assertNotNull(executable, "Executable action can not be null");
        assertSlotValid(inventoryItem);
        delegate.addButton(inventoryItem, executable);
    }

    @Override
    public void addItem(InventoryItem inventoryItem) {
        assertNotNull(inventoryItem, "Item can not be null");
        assertSlotValid(inventoryItem);
        delegate.addItem(inventoryItem);
    }

    @Override
    public void removeItem(int slot, int page) {
        assertSlotValid(slot);
        delegate.removeItem(slot, page);
    }

    private void assertSlotValid(int slot) {
        assertArgument(slot >= 0 && slot < MAX_INVENTORY_SIZE,
                "Slot must be greater than zero and less than " + MAX_INVENTORY_SIZE);
    }

    private void assertSlotValid(InventoryItem inventoryItem) {
        assertSlotValid(inventoryItem.getSlot());
    }

    private void assertNotNull(Object object, String message) {
        assertArgument(object != null, message);
    }

    private void assertArgument(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

}
