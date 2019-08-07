package me.riguron.bukkit.item.executable;

import org.bukkit.entity.Player;

/**
 * No-op ExecutableItem implementation for null object (special case)
 * pattern.
 */
public enum NoOpItem implements ExecutableItem {

    INSTANCE {
        @Override
        public void execute(Player player) {

        }
    };
}
