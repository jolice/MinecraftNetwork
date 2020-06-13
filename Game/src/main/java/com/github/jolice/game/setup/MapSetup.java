package com.github.jolice.game.setup;

import com.github.jolice.game.config.map.MapConfig;
import org.bukkit.Location;

public interface MapSetup {

    /**
     * Resets all defined locations.
     */
    void reset();

    MapConfig getConfig();

    void setSpectator(Location location);

    void setName(String name);

}
