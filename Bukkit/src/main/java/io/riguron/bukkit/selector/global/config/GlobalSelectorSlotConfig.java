package io.riguron.bukkit.selector.global.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bukkit.Material;

/**
 * Represents configuration of the selector slot.
 */
@Data
@Setter(AccessLevel.NONE)
public class GlobalSelectorSlotConfig {

    /**
     * Material of the icon.
     */
    private Material material;

    /**
     * Type of the server that is associated with the icon.
     */
    private String serverChannel;

    /**
     * Name of  the icon.
     */
    private String displayName;

    /**
     * Position of the icon in the selector interface.
     */
    private int position;

}
