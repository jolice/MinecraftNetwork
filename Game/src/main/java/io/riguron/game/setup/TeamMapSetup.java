package io.riguron.game.setup;

import lombok.Getter;
import org.bukkit.Location;
import io.riguron.game.config.map.MapConfig;
import io.riguron.game.config.map.team.TeamLocationConfig;
import io.riguron.game.config.map.team.TeamMapConfig;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class TeamMapSetup implements MapSetup {

    private TeamMapConfig teamMapConfig;

    public TeamMapSetup() {
        this.reset();
    }

    @Override
    public void reset() {
        this.teamMapConfig = new TeamMapConfig();
        this.teamMapConfig.setTeamLocationConfigs(new HashMap<>());
    }

    @Override
    public MapConfig getConfig() {
        return teamMapConfig;
    }

    @Override
    public void setSpectator(Location location) {
        teamMapConfig.setSpectator(location);
    }

    @Override
    public void setName(String name) {
        this.teamMapConfig.setName(name);
    }

    public void addTeamSpawn(String team, Location location) {
        teamMapConfig.getTeamLocationConfigs().computeIfAbsent(team, teamName -> {
            TeamLocationConfig teamLocationConfig = new TeamLocationConfig();
            teamLocationConfig.setTeamName(teamName);
            teamLocationConfig.setLocations(new ArrayList<>());
            return teamLocationConfig;
        }).getLocations().add(location);
    }


}
