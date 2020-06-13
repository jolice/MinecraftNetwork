package com.github.jolice.game.config.map.solo;

import com.github.jolice.game.config.map.MapConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.Location;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoloMapConfig extends MapConfig {


    private List<Location> spawnLocations;
}
