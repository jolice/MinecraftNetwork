package io.riguron.bukkit.selector.global.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * Represents a config of the global selector.
 */
@Data
@Setter(AccessLevel.NONE)
public class GlobalSelectorConfig {

    /**
     * List of slot configurations.
     */
    private List<GlobalSelectorSlotConfig> slots;
}
