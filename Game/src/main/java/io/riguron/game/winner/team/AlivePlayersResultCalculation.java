package io.riguron.game.winner.team;

import io.riguron.game.team.Team;
import io.riguron.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;
import io.riguron.game.team.Team;
import io.riguron.game.team.repository.Teams;

@RequiredArgsConstructor
public class AlivePlayersResultCalculation implements TeamResultCalculation {

    private final Teams teams;

    @Override
    public Team calculateWinningTeam() {
        return teams
                .getAllTeams()
                .stream()
                .filter(Team::isAlive)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
