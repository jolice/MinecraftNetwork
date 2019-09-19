package io.riguron.game.team.repository;

import io.riguron.game.team.Team;
import io.riguron.game.team.TeamPlayers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import io.riguron.game.team.Team;
import io.riguron.game.team.TeamPlayers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InMemoryTeamsTest {

    private Teams teams = new InMemoryTeams();

    @Test
    public void whenAddThenFound() {
        String teamName = addTeam(1);
        Optional<Team> foundTeam = teams.getByName(teamName);
        assertTrue(foundTeam.isPresent());
        assertEquals("name2", foundTeam.get().getName());
    }

    @Test
    public void whenGetTeamNamesThenReturnedNameSet() {
        for (int i = 0; i < 3; i++) {
            addTeam(i);
        }

        assertEquals(new HashSet<>(Arrays.asList("name1", "name2", "name3")),
                new HashSet<>(teams.getTeamNames())
        );
    }

    @Test
    public void whenGetAllTeamsThenReturnedAllTeams() {
        addTeam(1);
        addTeam(2);

        Set<String> names = teams.getAllTeams().stream().map(Team::getName).
                collect(Collectors.toSet());

        assertEquals(new HashSet<>(Arrays.asList("name2", "name3")),
                names);
    }

    @Test
    public void whenGetMostFreeTeamThenReturnedTeamWithMinPlayers() {

        addTeam(1, 5);
        addTeam(2, 7);
        addTeam(3, 4);

        // min is team #3, so name is name(3+1)
        Assert.assertEquals("name4", teams.getMostFreeTeam().getName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAttemptToOverwriteExistingTeamThenFailed() {
        addTeam(1);
        addTeam(1);
    }

    @Test
    public void whenGetAliveTeamsThenReturnedOnlyAliveTeams() {
        addTeam(1, 0);
        addTeam(2, 2);
        addTeam(3, 4);
        addTeam(4, 5);
        addTeam(5, 0);
        assertEquals(3, teams.getAliveTeams());
    }

    private String addTeam(int i) {
        return addTeam(i, 0);
    }

    private String addTeam(int i, int onlinePlayers) {
        Team team = mock(Team.class);
        when(team.getPlayers()).thenAnswer((Answer<TeamPlayers>) invocation -> {
            TeamPlayers teamPlayers = mock(TeamPlayers.class);
            when(teamPlayers.count()).thenReturn(onlinePlayers);
            return teamPlayers;
        });
        when(team.isAlive()).thenReturn(onlinePlayers > 0);
        when(team.getName()).thenReturn("name" + (i + 1));
        teams.addTeam(team);
        return team.getName();
    }

}