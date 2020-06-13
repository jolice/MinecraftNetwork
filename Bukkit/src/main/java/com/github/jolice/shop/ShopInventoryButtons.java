package com.github.jolice.shop;

import com.github.jolice.shop.ui.ShopInventoryConfiguration;
import com.github.jolice.gui.Executable;
import com.github.jolice.gui.item.InventoryItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import io.riguron.system.shop.ActivePurchases;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ShopInventoryButtons {

    private final Server server;
    private final ActivePurchases activePurchases;

    public Map<InventoryItem, Executable> getButtons() {
        Map<InventoryItem, Executable> map = new HashMap<>();
        map.put(ShopInventoryConfiguration.CANCEL, playerId -> server.getPlayer(playerId).closeInventory());
        map.put(ShopInventoryConfiguration.CONFIRM, activePurchases::confirmPurchase);
        return map;
    }

}
