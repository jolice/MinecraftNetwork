package com.github.jolice.game.module.team;

import com.github.jolice.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;
import com.github.jolice.config.client.ConfigurationLoader;
import com.github.jolice.game.config.map.team.TeamLocationConfig;
import com.github.jolice.game.config.map.team.TeamMapConfig;
import com.github.jolice.game.map.GameMap;
import com.github.jolice.game.map.type.TeamGameMap;
import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.team.TeamPlayer;
import com.github.jolice.game.team.Team;
import org.bukkit.Location;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TeamMapsLoader {

    private final ConfigurationLoader configurationLoader;
    private final GamePlayerStorage<TeamPlayer> teamPlayerRepository;
    private final Teams teams;

    public List<GameMap> loadTeamMaps() {
        return configurationLoader
                .loadAll("maps", TeamMapConfig.class)
                .stream()
                .map(teamMapConfig -> new TeamGameMap(teamMapConfig.getName(), teamPlayerRepository, getTeamSpawns(teamMapConfig, teams)))
                .collect(Collectors.toList());
    }

    private Map<Team, List<Location>> getTeamSpawns(TeamMapConfig teamMapConfig, Teams teams) {
        return teamMapConfig.getTeamLocationConfigs().values()
                .stream()
                .collect(
                        Collectors.toMap(x -> teams.getByName(x.getTeamName()).orElseThrow(() -> new IllegalArgumentException(x.getTeamName())),
                                TeamLocationConfig::getLocations)
                );
    }
}
