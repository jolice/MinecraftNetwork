package me.riguron.game.winner.team;

import lombok.RequiredArgsConstructor;
import me.riguron.game.team.Team;
import me.riguron.game.team.repository.Teams;

import java.util.Comparator;

@RequiredArgsConstructor
public class ScoreTeamResultCalculation implements TeamResultCalculation {

    private final Teams teams;

    @Override
    public Team calculateWinningTeam() {
        return teams
                .getAllTeams()
                .stream()
                .max(Comparator.comparing(Team::getScore))
                .orElseThrow(IllegalStateException::new);
    }
}
