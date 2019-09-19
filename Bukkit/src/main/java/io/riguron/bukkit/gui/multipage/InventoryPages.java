package io.riguron.bukkit.gui.multipage;

import org.bukkit.Server;
import org.bukkit.inventory.Inventory;

public class InventoryPages {

    private static final int INVENTORY_SIZE = 54;

    private final PagesList pages;
    private final Server server;
    private final PageBuilder pageBuilder;
    private final Inventory emptyInventory;

    public InventoryPages(PagesList pages, Server server, PageBuilder pageBuilder) {
        this.pages = pages;
        this.server = server;
        this.pageBuilder = pageBuilder;
        this.emptyInventory = server.createInventory(null, INVENTORY_SIZE, "");
    }

    public void clearAll() {
        while (pages.size() != 1) {
            pages.remove(pages.size() - 1);
        }

        this.pages.get(0).clear();
    }

    public int getTotalPages() {
        return pages.size();
    }

    public boolean isEmpty() {
        return pages.isEmpty();
    }

    public Inventory getPage(int page) {
        if (pages.isEmpty()) {
            return emptyInventory;
        }
        return pages.get(page - 1);
    }

    public void addNewPage() {
        Inventory newPage = server.createInventory(null, INVENTORY_SIZE, pageBuilder.pageTitle(Math.max(1, pages.size()), pages.size() + 1));
        this.pages.add(newPage);
        for (int i = 0; i < pages.size() - 1; i++) {
            this.pageBuilder.rebuildPageByIndex(i);
        }
        this.pageBuilder.rebuildPageSwitchers(pages.size() - 2);
        this.pageBuilder.addSwitchItems(pages.size() - 1, newPage);
    }

    public void removePage(int page) {
        int pageIndex = page - 1;
        pages.remove(pageIndex);
        for (int i = 0; i < pages.size(); i++) {
            this.pageBuilder.rebuildPageByIndex(i);
        }
        this.pageBuilder.rebuildSwitchersAfterRemoval(pageIndex);
    }


}
