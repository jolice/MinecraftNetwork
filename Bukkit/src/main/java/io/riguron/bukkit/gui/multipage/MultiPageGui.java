package io.riguron.bukkit.gui.multipage;

import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.GUI;
import io.riguron.bukkit.gui.multipage.player.PlayerPageRearranger;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.GUI;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.gui.multipage.player.PlayerPageRearranger;
import io.riguron.bukkit.gui.multipage.player.PlayerPages;

import java.util.Set;
import java.util.UUID;

/**
 * Represents an inventory GUI that may contain multiple pages. Players can
 * switch pages by clicking appropriate buttons.
 * <p>
 * Each page, except the utmost ones, has two "switchers" - buttons that are
 * used for navigating between the pages. There are two types of switchers:
 * "forward" and "back", used for moving one page forward and back respectively.
 * <p>
 * Leftmost page has only "forward" button, and the rightmost one only the "back"
 * button.
 */
@RequiredArgsConstructor
public class MultiPageGui implements GUI {

    private final PlayerPages playerPages;
    private final InventoryPages inventoryPages;
    private final SlotActions slotActions;
    private final Server server;
    private final PlayerPageRearranger playerPageRearranger;

    @Override
    public void displayTo(Player player) {
        playerPages.setPlayerPage(player.getUniqueId(), 1);
        player.openInventory(inventoryPages.getPage(1));
    }

    @Override
    public void clear() {
        this.inventoryPages.clearAll();
        this.slotActions.clear();
        Set<UUID> activeSessions = this.playerPages.getActiveSessions();
        this.playerPages.clear();
        activeSessions.forEach(uuid -> {
            playerPages.setPlayerPage(uuid, 1);
            server.getPlayer(uuid).closeInventory();
            displayTo(server.getPlayer(uuid));
        });
    }

    @Override
    public void addButton(InventoryItem inventoryItem, Executable executable) {
        this.addItem(inventoryItem);
        this.slotActions.addSlotAction(inventoryItem, executable);
    }

    @Override
    public void addItem(InventoryItem item) {
        int page = item.getPage();
        if (page > inventoryPages.getTotalPages()) {
            inventoryPages.addNewPage();
        }
        this.addItem(inventoryPages.getPage(page), item);
    }

    @Override
    public void removeItem(int slot, int page) {
        Inventory inventory = inventoryPages.getPage(page);
        inventory.removeItem(inventory.getItem(slot));
        slotActions.removeSlotAction(page, slot);
        if (inventory.getContents().length == 0 && !inventoryPages.isEmpty()) {
            inventoryPages.removePage(page);
            slotActions.shiftAfterPageRemoval(page);
            playerPageRearranger.rearrangeAfterRemoval(page);
        }
    }


    private void addItem(Inventory inventory, InventoryItem inventoryItem) {
        inventory.setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
    }


}
