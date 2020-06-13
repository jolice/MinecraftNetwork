package com.github.jolice.item.executable;

import org.bukkit.entity.Player;

/**
 * Represents an item causing some action to happen when it's clicked
 * (interacted) by the player.
 */
public interface ExecutableItem {

    /**
     * Action that happens when item is clicked.
     *
     * @param player player that clicked the item
     */
    void execute(Player player);


}
