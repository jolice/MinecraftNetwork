package io.riguron.game.team.repository;

import io.riguron.game.team.Team;

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
