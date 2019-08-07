package me.riguron.game.module.team;

import lombok.RequiredArgsConstructor;
import me.riguron.config.client.ConfigurationLoader;
import me.riguron.game.config.map.team.TeamLocationConfig;
import me.riguron.game.config.map.team.TeamMapConfig;
import me.riguron.game.map.GameMap;
import me.riguron.game.map.type.TeamGameMap;
import me.riguron.game.player.repository.GamePlayerStorage;
import me.riguron.game.player.team.TeamPlayer;
import me.riguron.game.team.Team;
import me.riguron.game.team.repository.Teams;
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
