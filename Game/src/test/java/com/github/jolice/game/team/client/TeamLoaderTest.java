package com.github.jolice.game.team.client;

import com.github.jolice.game.config.team.TeamConfig;
import com.github.jolice.game.config.team.TeamGameConfig;
import com.github.jolice.game.team.repository.Teams;
import org.bukkit.DyeColor;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class TeamLoaderTest {

    @Test
    public void loadTeams() {

        TeamGameConfig teamGameConfig = mock(TeamGameConfig.class);
        Teams teams = mock(Teams.class);

        TeamLoader teamLoader = new TeamLoader(teamGameConfig, teams);

        TeamConfig config = mock(TeamConfig.class);
        when(config.getColor()).thenReturn(DyeColor.BLACK);
        when(config.getName()).thenReturn("name");
        when(teamGameConfig.getTeamSize()).thenReturn(4);
        when(teamGameConfig.getTeams()).thenReturn(Collections.singletonList(config));

        teamLoader.loadTeams();

        verify(teams).addTeam(argThat(
                team -> team.getName().equals("name") &&
                        team.getColor().equals(DyeColor.BLACK)));
    }
}