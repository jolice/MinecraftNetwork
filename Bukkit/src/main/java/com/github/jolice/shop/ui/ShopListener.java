package com.github.jolice.shop.ui;

import com.github.jolice.shop.ShopViews;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import io.riguron.system.shop.ActivePurchases;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class ShopListener implements Listener {

    private final ShopViews shopViews;
    private final ActivePurchases activePurchases;

    public ShopListener(ActivePurchases activePurchases, ShopViews shopViews) {
        this.shopViews = shopViews;
        this.activePurchases = activePurchases;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        if (shopViews.isInShop(id)) {
            activePurchases.removeTransaction(id);
            shopViews.remove(id);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        activePurchases.removeTransaction(event.getPlayer().getUniqueId());
    }

}
