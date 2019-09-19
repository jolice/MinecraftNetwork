package io.riguron.bukkit.item.executable;

import lombok.Value;
import io.riguron.bukkit.gui.item.InventoryItem;

@Value
public class ExecutableItemRegistration {

    private final InventoryItem item;
    private final ExecutableItem executableItem;
}
