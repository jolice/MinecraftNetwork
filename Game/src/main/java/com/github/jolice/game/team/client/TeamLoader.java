package com.github.jolice.game.team.client;

import com.github.jolice.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.config.team.TeamConfig;
import com.github.jolice.game.config.team.TeamGameConfig;
import com.github.jolice.game.team.StandardTeam;
import com.github.jolice.game.team.Team;

@RequiredArgsConstructor
public class TeamLoader {

    private final TeamGameConfig teamGameConfig;
    private final Teams teams;

    public void loadTeams() {
        this.teamGameConfig
                .getTeams()
                .stream()
                .map(this::mapSingleTeamConfig)
                .forEach(teams::addTeam);
    }

    private Team mapSingleTeamConfig(TeamConfig singleTeamConfig) {
        return new StandardTeam(singleTeamConfig.getColor(), singleTeamConfig.getName(), teamGameConfig.getTeamSize());
    }
}
