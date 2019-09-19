package io.riguron.game.team.client;

import org.bukkit.DyeColor;
import org.junit.Test;
import io.riguron.game.config.team.TeamConfig;
import io.riguron.game.config.team.TeamGameConfig;
import io.riguron.game.team.repository.Teams;

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