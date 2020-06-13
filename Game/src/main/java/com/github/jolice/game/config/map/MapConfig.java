package com.github.jolice.game.config.map;

import lombok.Data;
import org.bukkit.Location;
import com.github.jolice.config.Configuration;

@Data
public class MapConfig implements Configuration {

    private String name;
    private Location spectator;
}
