package com.github.jolice.game.winner.team;

import com.github.jolice.game.team.Team;
import com.github.jolice.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;

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
