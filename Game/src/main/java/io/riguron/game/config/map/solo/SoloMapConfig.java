package io.riguron.game.config.map.solo;

import io.riguron.game.config.map.MapConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.Location;
import io.riguron.game.config.map.MapConfig;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoloMapConfig extends MapConfig {


    private List<Location> spawnLocations;
}
