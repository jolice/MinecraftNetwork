package com.github.jolice.item.executable;

import lombok.Value;
import com.github.jolice.gui.item.InventoryItem;

@Value
public class ExecutableItemRegistration {

    private final InventoryItem item;
    private final ExecutableItem executableItem;
}
