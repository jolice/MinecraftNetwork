package io.riguron.bukkit.shop;

import io.riguron.bukkit.gui.GUI;
import io.riguron.bukkit.gui.GUIFactory;
import io.riguron.bukkit.gui.item.InventoryItem;
import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.gui.GUI;
import io.riguron.bukkit.gui.GUIFactory;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.item.ItemStackBuilder;
import io.riguron.bukkit.shop.ui.ShopInventoryConfiguration;
import io.riguron.system.shop.Purchasable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class ShopViews  {

    private final GUIFactory guiFactory;
    private final Set<UUID> activeShopViews = new HashSet<>();
    private final ShopInventoryButtons buttons;

    public GUI openShopView(UUID playerId, Purchasable purchasable) {
        GUI playerView = constructInventory(purchasable);
        activeShopViews.add(playerId);
        return playerView;
    }

    private GUI constructInventory(Purchasable purchasable) {
        GUI inventory = guiFactory.newSinglePageGui(ShopInventoryConfiguration.TITLE);
        inventory.addItem(new InventoryItem(createInfoButton(purchasable), ShopInventoryConfiguration.INFO_BUTTON_POSITION));
        buttons.getButtons().forEach(inventory::addButton);
        return inventory;
    }

    private ItemStack createInfoButton(Purchasable purchasable) {
        return new ItemStackBuilder(Material.PAPER)
                .displayName("Confirm purchase")
                .lore()
                .emptyLine()
                .addLine(String.format("You are about to purchase %s for %d", purchasable.getDescription(), purchasable.getPrice()))
                .emptyLine()
                .addLine("Click green button to confirm your purchase")
                .build();
    }

    public boolean isInShop(UUID uuid) {
        return activeShopViews.contains(uuid);
    }

    public void remove(UUID uuid) {
        this.activeShopViews.remove(uuid);
    }



}

