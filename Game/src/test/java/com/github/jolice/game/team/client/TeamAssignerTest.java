package com.github.jolice.game.team.client;

import com.github.jolice.game.player.repository.GamePlayerStorage;
import com.github.jolice.game.player.team.TeamPlayer;
import com.github.jolice.game.team.Team;
import com.github.jolice.game.team.TeamPlayers;
import com.github.jolice.game.team.repository.Teams;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TeamAssignerTest {

    private List<TeamPlayer> players = new ArrayList<>();

    @Test
    public void assignTeams() {

        GamePlayerStorage<TeamPlayer> storage = mock(GamePlayerStorage.class);
        Teams teams = mock(Teams.class);
        when(storage.getAllPlayers()).thenReturn(players);

        TeamAssigner teamAssigner = new TeamAssigner(storage, teams);

        TeamPlayers teamPlayers = mock(TeamPlayers.class);
        Team mostFree = mock(Team.class);
        when(teams.getMostFreeTeam()).thenReturn(mostFree);
        when(mostFree.getPlayers()).thenReturn(teamPlayers);

        addPlayer(true);
        addPlayer(false);
        addPlayer(false);
        addPlayer(true);

        teamAssigner.assignRemaining();


        verify(teamPlayers, times(0)).add(eq(players.get(0)));
        verify(teamPlayers, times(1)).add(eq(players.get(1)));
        verify(teamPlayers, times(1)).add(eq(players.get(2)));
        verify(teamPlayers, times(0)).add(eq(players.get(3)));

    }

    private void addPlayer(boolean inTeam) {
        TeamPlayer teamPlayer = mock(TeamPlayer.class);
        when(teamPlayer.isInTeam()).thenReturn(inTeam);
        players.add(teamPlayer);
    }

}