package com.github.jolice.gui.single;

import com.github.jolice.gui.Executable;
import com.github.jolice.gui.item.NoOpExecutable;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class SinglePageListener  {

    private Map<Integer, Executable> slotActions = new HashMap<>();

    public void inventoryClicked(InventoryClickEvent event) {
        slotActions
                .getOrDefault(event.getSlot(), NoOpExecutable.INSTANCE)
                .execute(event.getWhoClicked().getUniqueId());
        event.setCancelled(true);
    }

    public void addHandler(Integer slot, Executable executable) {
        slotActions.put(slot, executable);
    }
}
