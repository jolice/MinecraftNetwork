package com.github.jolice.item.executable;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Container class storing mapping of Bukkit item stacks to executable items.
 */
public class ExecutableItems {

    private Map<ItemStack, ExecutableItem> items = new HashMap<>();

    public void add(ExecutableItemRegistration registration) {
        items.put(registration.getItem().getItemStack(), registration.getExecutableItem());
    }

    public void dispatchClick(Player player, ItemStack itemStack) {
        items.getOrDefault(itemStack, NoOpItem.INSTANCE).execute(player);
    }

}
