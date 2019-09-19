package io.riguron.bukkit.gui.item;

import io.riguron.bukkit.gui.Executable;

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
