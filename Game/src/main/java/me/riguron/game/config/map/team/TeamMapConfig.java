package me.riguron.game.config.map.team;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.riguron.game.config.map.MapConfig;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeamMapConfig extends MapConfig {


    private Map<String, TeamLocationConfig> teamLocationConfigs;
}
