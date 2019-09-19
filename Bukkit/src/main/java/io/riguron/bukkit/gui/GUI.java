package io.riguron.bukkit.gui;

import org.bukkit.entity.Player;
import io.riguron.bukkit.gui.item.InventoryItem;

/**
 * Visual user interface represented by Minecraft inventory of size 54.
 */
public interface GUI {

    /**
     * Displays a GUI to a specified player by opening an appropriate
     * inventory and showing it to the player.
     *
     * @param player player to which the inventory is displayed
     */
    void displayTo(Player player);

    /**
     * Clears the inventory by removing all of its items.
     */
    void clear();

    /**
     * Adds new clickable item to an inventory. Item is paired
     * with the specified Executable instance and causes its execution
     * when clicked.
     *
     * @param inventoryItem an icon of clickable button
     * @param executable    action that is performed on item click
     */
    void addButton(InventoryItem inventoryItem, Executable executable);

    /**
     * Adds non-clickable item to an inventory. Nothing happens when this
     * item is clicked.
     *
     * @param inventoryItem item to be added
     */
    void addItem(InventoryItem inventoryItem);

    /**
     * Removes an item at specified page and slot.
     *
     * @param slot slot of item to be removed
     * @param page page of item to be removed
     */
    void removeItem(int slot, int page);


}
