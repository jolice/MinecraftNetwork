package io.riguron.bukkit.shop.ui;

import io.riguron.bukkit.gui.item.InventoryItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.item.ItemStackBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopInventoryConfiguration {

    public static final String TITLE = "Shop";

    public static final InventoryItem CONFIRM = new InventoryItem(
            new ItemStackBuilder(Material.EMERALD).displayName("Confirm").build(),
            10
    );

    public static final InventoryItem CANCEL = new InventoryItem(
            new ItemStackBuilder(Material.REDSTONE_BLOCK).displayName("Cancel").build(),
            15
    );


    public static final int INFO_BUTTON_POSITION = 3;
}