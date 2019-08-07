package me.riguron.bukkit.item.join;

import lombok.Value;
import me.riguron.bukkit.gui.item.InventoryItem;
import me.riguron.bukkit.item.executable.ExecutableItem;

@Value
public class JoinItem {

    private final InventoryItem inventoryItem;
    private final ExecutableItem executableItem;
}
