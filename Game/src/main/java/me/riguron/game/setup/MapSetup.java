package me.riguron.game.setup;

import org.bukkit.Location;
import me.riguron.game.config.map.MapConfig;

public interface MapSetup {

    /**
     * Resets all defined locations.
     */
    void reset();

    MapConfig getConfig();

    void setSpectator(Location location);

    void setName(String name);

}
