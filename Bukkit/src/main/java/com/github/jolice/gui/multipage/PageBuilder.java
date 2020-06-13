package com.github.jolice.gui.multipage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import com.github.jolice.gui.item.InventoryItem;

import static com.github.jolice.gui.multipage.MultiPageButtons.NEXT_PAGE;
import static com.github.jolice.gui.multipage.MultiPageButtons.PREVIOUS_PAGE;

@RequiredArgsConstructor
public class PageBuilder {

    private static final int INVENTORY_SIZE = 54;

    private final String title;
    private final Server server;
    private final PagesList pages;

    public void rebuildSwitchersAfterRemoval(int removedPageIndex) {
        if (removedPageIndex == 0) {
            this.rebuildPageSwitchers(removedPageIndex);
        }
        if (removedPageIndex == pages.size()) {
            this.rebuildPageSwitchers(removedPageIndex - 1);
        }
    }

    public void rebuildPageSwitchers(int pageIndex) {
        Inventory pageToRebuild = pages.get(pageIndex);
        pageToRebuild.removeItem(PREVIOUS_PAGE.getItemStack());
        pageToRebuild.removeItem(NEXT_PAGE.getItemStack());
        this.addSwitchItems(pageIndex, pageToRebuild);
    }

    public void addSwitchItems(int i, Inventory page) {
        if (i > 0 && i <= pages.size() - 1) {
            this.addItem(page, PREVIOUS_PAGE);
        }

        if (i >= 0 && i < pages.size() - 1) {
            this.addItem(page, NEXT_PAGE);
        }
    }

    public void rebuildPageByIndex(int index) {
        Inventory current = pages.get(index);
        String newTitle = pageTitle(index, pages.size());
        Inventory newInventory = server.createInventory(null, INVENTORY_SIZE, newTitle);
        for (int i = 0; i < current.getSize(); i++) {
            newInventory.setItem(i, current.getItem(i));
        }
        this.pages.set(index, newInventory);
    }

    public String pageTitle(int index, int total) {
        return String.format("%s (page %d/%d)", title, index, total);
    }

    private void addItem(Inventory inventory, InventoryItem inventoryItem) {
        inventory.setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
    }


}
