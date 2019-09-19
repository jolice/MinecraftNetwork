package io.riguron.game.module.team;

import lombok.RequiredArgsConstructor;
import io.riguron.config.client.ConfigurationLoader;
import io.riguron.game.config.map.team.TeamLocationConfig;
import io.riguron.game.config.map.team.TeamMapConfig;
import io.riguron.game.map.GameMap;
import io.riguron.game.map.type.TeamGameMap;
import io.riguron.game.player.repository.GamePlayerStorage;
import io.riguron.game.player.team.TeamPlayer;
import io.riguron.game.team.Team;
import io.riguron.game.team.repository.Teams;
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
