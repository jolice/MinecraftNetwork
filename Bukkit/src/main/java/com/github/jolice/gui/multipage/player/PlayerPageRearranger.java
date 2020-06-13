package com.github.jolice.gui.multipage.player;

import com.github.jolice.gui.multipage.InventoryPages;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerPageRearranger {

    private final PlayerPages playerPages;
    private final InventoryPages inventoryPages;
    private final Server server;

    public void rearrangeAfterRemoval(int page) {
        int newInventoryIndex = determineNextInventoryIndex(page);
        Set<UUID> currentlyLooking = playerPages.rearrangeAfterRemoval(page);
        currentlyLooking.forEach(uuid -> {
            server.getPlayer(uuid).closeInventory();
            server.getPlayer(uuid).openInventory(inventoryPages.getPage(newInventoryIndex));
            playerPages.setPlayerPage(uuid, newInventoryIndex);
        });
    }

    private int determineNextInventoryIndex(int page) {
        if (page == 1) {
            return page;
        } else if (page == inventoryPages.getTotalPages()) {
            return inventoryPages.getTotalPages() - 1;
        } else {
            return page + 1;
        }
    }


}
