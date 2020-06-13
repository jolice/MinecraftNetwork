package com.github.jolice.game.team.repository;

import com.github.jolice.game.team.Team;

import java.util.Optional;
import java.util.Set;

public interface Teams {

    Optional<Team> getByName(String name);

    Set<String> getTeamNames();

    Team getMostFreeTeam();

    void addTeam(Team team);

    Set<Team> getAllTeams();

    int getAliveTeams();


}
