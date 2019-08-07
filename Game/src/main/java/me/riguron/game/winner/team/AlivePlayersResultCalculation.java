package me.riguron.game.winner.team;

import lombok.RequiredArgsConstructor;
import me.riguron.game.team.Team;
import me.riguron.game.team.repository.Teams;

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
