package com.github.jolice.game.winner.team;

import com.github.jolice.game.team.Team;
import com.github.jolice.game.team.repository.Teams;
import lombok.RequiredArgsConstructor;

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
