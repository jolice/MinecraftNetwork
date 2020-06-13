package com.github.jolice.selector.global;

import com.github.jolice.selector.global.config.GlobalSelectorConfig;
import com.github.jolice.selector.global.config.GlobalSelectorSlotConfig;
import com.github.jolice.gui.GUI;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import com.github.jolice.gui.item.InventoryItem;
import com.github.jolice.gui.single.SinglePageInterface;
import com.github.jolice.item.ItemStackBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GlobalSelectorInventory implements GlobalSelectorInterface {

    private static final String INVENTORY_TITLE = "Choose a server";
    private static final String ONLINE_FORMAT = "Players onlinePlayers: %d";

    private final GUI gui;
    private final GlobalSelectorConfig globalSelectorConfig;
    private final Map<String, GlobalSelectorIcon> items = new HashMap<>();
    private final GlobalSelectorItemFactory factory;

    public GlobalSelectorInventory(Plugin plugin, GlobalSelectorItemFactory factory, GlobalSelectorConfig globalSelectorConfig) {
        this.gui = new SinglePageInterface(plugin, INVENTORY_TITLE);
        this.factory = factory;
        this.globalSelectorConfig = globalSelectorConfig;
    }

    @Override
    public void update(Map<String, Integer> online) {
        if (items.isEmpty()) {
            fill();
        }
        online.forEach((key, val) -> items.get(key).update(val));
    }

    private void fill() {
        globalSelectorConfig.getSlots().forEach(slotConfig -> {
            InventoryItem inventoryItem = new InventoryItem(this.buildIcon(slotConfig), slotConfig.getPosition());
            this.gui.addButton(inventoryItem, factory.newGlobalSelectorButton(slotConfig.getServerChannel()));
            this.items.put(slotConfig.getServerChannel(), new GlobalSelectorIcon(inventoryItem));
        });
    }

    private ItemStack buildIcon(GlobalSelectorSlotConfig slotConfig) {
        return new ItemStackBuilder(slotConfig.getMaterial())
                .displayName(slotConfig.getDisplayName())
                .lore()
                .emptyLine()
                .addLine(String.format(ONLINE_FORMAT, 0))
                .emptyLine()
                .build();
    }

    @RequiredArgsConstructor
    private static class GlobalSelectorIcon {

        private final InventoryItem inventoryItem;

        private void update(int online) {
            inventoryItem.getItemStack().getItemMeta().setLore(
                    Arrays.asList("", String.format(ONLINE_FORMAT, online), "")
            );
        }
    }

}
