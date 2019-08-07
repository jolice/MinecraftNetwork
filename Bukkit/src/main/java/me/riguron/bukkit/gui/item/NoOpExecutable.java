package me.riguron.bukkit.gui.item;

import me.riguron.bukkit.gui.Executable;

import java.util.UUID;

/**
 * No-Op implementation for the NullObject (Special Case)
 * pattern.
 */
public enum NoOpExecutable implements Executable {

    INSTANCE {
        @Override
        public void execute(UUID playerId) {
        }
    };

}
