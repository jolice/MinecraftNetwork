package com.github.jolice.game.config.map.team;

import com.github.jolice.game.config.map.MapConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeamMapConfig extends MapConfig {


    private Map<String, TeamLocationConfig> teamLocationConfigs;
}
