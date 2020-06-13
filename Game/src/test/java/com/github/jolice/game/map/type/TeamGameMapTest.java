package com.github.jolice.game.map.type;

import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.team.TeamPlayer;
import com.github.jolice.game.team.Team;
import org.bukkit.Location;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TeamGameMapTest {

    private GamePlayerStorage<TeamPlayer> playerStorage;
    private Map<Team, List<Location>> configuredTeamLocations;

    @Test
    public void whenTeleportAllPlayersThenTeleported() {

        runAssertions((teams, teamGameMap) -> {
            List<TeamPlayer> allPlayers = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                TeamPlayer teamPlayer = mock(TeamPlayer.class);
                when(teamPlayer.getTeam()).thenReturn(teams.get(i % teams.size()));
                allPlayers.add(teamPlayer);
            }

            when(playerStorage.getAllPlayers()).thenReturn(allPlayers);
            teamGameMap.teleportPlayers();

            for (TeamPlayer allPlayer : allPlayers) {
                verify(allPlayer).teleport(argThat(
                        location -> configuredTeamLocations
                                .get(allPlayer.getTeam()).contains(location)));
            }

        });
    }

    @Test
    public void whenTeleportSinglePlayerThenTeleported() {
        runAssertions((teams, teamGameMap) -> {

            TeamPlayer gamePlayer = mock(TeamPlayer.class);
            UUID uuid = UUID.randomUUID();
            when(playerStorage.getPlayer(eq(uuid))).thenReturn(gamePlayer);
            when(gamePlayer.getTeam()).thenReturn(teams.get(0));

            teamGameMap.teleportPlayer(uuid);

            verify(gamePlayer).teleport(argThat(location -> configuredTeamLocations.get(gamePlayer.getTeam()).contains(location)));
        });
    }

    private void runAssertions(BiConsumer<List<Team>, TeamGameMap> action) {


        configuredTeamLocations = configuredTeamLocations();

        List<Team> teams = new ArrayList<>(configuredTeamLocations.keySet());

        playerStorage = mock(GamePlayerStorage.class);

        TeamGameMap teamGameMap = new TeamGameMap("name", playerStorage, configuredTeamLocations);
        action.accept(teams, teamGameMap);

    }

    private Map<Team, List<Location>> configuredTeamLocations() {
        Map<Team, List<Location>> configuredTeamLocations = new HashMap<>();

        Map.Entry<Team, List<Location>> red = getTeam();
        Map.Entry<Team, List<Location>> blue = getTeam();
        Map.Entry<Team, List<Location>> white = getTeam();

        configuredTeamLocations.put(red.getKey(), red.getValue());
        configuredTeamLocations.put(blue.getKey(), blue.getValue());
        configuredTeamLocations.put(white.getKey(), white.getValue());

        return configuredTeamLocations;
    }

    private Map.Entry<Team, List<Location>> getTeam() {
        Team team = mock(Team.class);
        List<Location> teamLocations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            teamLocations.add(mock(Location.class));
        }
        return new AbstractMap.SimpleEntry<>(team, teamLocations);
    }
}