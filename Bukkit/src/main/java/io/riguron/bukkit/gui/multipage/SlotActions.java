package io.riguron.bukkit.gui.multipage;

import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.gui.item.NoOpExecutable;
import io.riguron.bukkit.gui.multipage.player.PlayerPages;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class SlotActions {

    private static final int INVENTORY_SIZE = 54;

    private final PlayerPages playerPages;
    private final Map<Integer, Executable> actions = new HashMap<>();

    public void executeSlotAction(UUID playerId, int sourceSlot) {
        int currentPage = playerPages.getPlayerPage(playerId);
        int targetSlot = targetSlot(sourceSlot, currentPage);
        actions.getOrDefault(targetSlot, NoOpExecutable.INSTANCE).execute(playerId);
    }

    public void shiftAfterPageRemoval(int removedPage) {
        Map<Integer, Executable> shiftedIndexes = new HashMap<>();
        for (Map.Entry<Integer, Executable> entry : actions.entrySet()) {
            int key = entry.getKey();
            if (key / INVENTORY_SIZE != removedPage) {
                if (key >= (removedPage + 1) * INVENTORY_SIZE) {
                    key = key - INVENTORY_SIZE;
                }
                shiftedIndexes.put(key, entry.getValue());
            }
        }
        this.actions.clear();
        this.actions.putAll(shiftedIndexes);
    }

    public void removeSlotAction(int page, int slot) {
        this.actions.remove(targetSlot(slot, page));
    }

    public void addSlotAction(InventoryItem inventoryItem, Executable executable) {
        this.actions.put(targetSlot(inventoryItem.getSlot(), inventoryItem.getPage()), executable);
    }

    private int targetSlot(int slot, int page) {
        return slot + (page * INVENTORY_SIZE);
    }

    public void clear() {
        this.actions.clear();
    }
}
