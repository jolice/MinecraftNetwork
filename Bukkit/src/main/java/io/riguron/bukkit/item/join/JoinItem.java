package io.riguron.bukkit.item.join;

import lombok.Value;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.item.executable.ExecutableItem;

@Value
public class JoinItem {

    private final InventoryItem inventoryItem;
    private final ExecutableItem executableItem;
}
