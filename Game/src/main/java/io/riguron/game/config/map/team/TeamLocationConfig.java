package io.riguron.game.config.map.team;

import lombok.Data;
import org.bukkit.Location;

import java.util.List;

@Data
public class TeamLocationConfig {

    private String teamName;
    private List<Location> locations;



}
