package me.riguron.bukkit.shop;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import me.riguron.bukkit.gui.Executable;
import me.riguron.bukkit.gui.item.InventoryItem;
import me.riguron.bukkit.shop.ui.ShopInventoryConfiguration;
import me.riguron.system.shop.ActivePurchases;

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
