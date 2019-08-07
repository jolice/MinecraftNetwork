package me.riguron.bukkit.item.join;

import me.riguron.bukkit.item.executable.ExecutableItemRegistration;
import me.riguron.bukkit.item.executable.ExecutableItems;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Items given to the player when he joins.
 */
public class JoinItems {

    private final Set<JoinItem> items;

    public JoinItems(Set<JoinItem> items, ExecutableItems executableItems) {
        this.items = items;
        this.items.forEach(joinItem -> executableItems.add(
                new ExecutableItemRegistration(
                        joinItem.getInventoryItem(),
                        joinItem.getExecutableItem()
                )
        ));
    }

    /**
     * Puts all registered items to the player's inventory.
     *
     * @param player player that receives the items.
     */
    public void giveTo(Player player) {
        items.stream()
                .map(JoinItem::getInventoryItem)
                .forEach(item -> player.getInventory().setItem(item.getSlot(), item.getItemStack()));
    }
}
