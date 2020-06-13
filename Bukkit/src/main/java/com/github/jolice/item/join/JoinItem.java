package com.github.jolice.item.join;

import com.github.jolice.item.executable.ExecutableItem;
import lombok.Value;
import com.github.jolice.gui.item.InventoryItem;

@Value
public class JoinItem {

    private final InventoryItem inventoryItem;
    private final ExecutableItem executableItem;
}
