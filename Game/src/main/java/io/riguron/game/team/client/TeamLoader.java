package io.riguron.game.team.client;

import lombok.RequiredArgsConstructor;
import io.riguron.game.config.team.TeamConfig;
import io.riguron.game.config.team.TeamGameConfig;
import io.riguron.game.team.StandardTeam;
import io.riguron.game.team.Team;
import io.riguron.game.team.repository.Teams;

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
