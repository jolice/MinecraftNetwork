package me.riguron.bukkit.item.executable;

import lombok.Value;
import me.riguron.bukkit.gui.item.InventoryItem;

@Value
public class ExecutableItemRegistration {

    private final InventoryItem item;
    private final ExecutableItem executableItem;
}
