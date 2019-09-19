package io.riguron.bukkit.shop;

import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.item.InventoryItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.gui.item.InventoryItem;
import io.riguron.bukkit.shop.ui.ShopInventoryConfiguration;
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
