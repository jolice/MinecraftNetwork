package com.github.jolice.game.team.repository;

import com.github.jolice.game.team.Team;

import java.util.*;


public class InMemoryTeams implements Teams {

    private Map<String, Team> teams = new HashMap<>();

    @Override
    public Optional<Team> getByName(String name) {
        return Optional.ofNullable(teams.get(name));
    }

    @Override
    public Set<String> getTeamNames() {
        return teams.keySet();
    }

    @Override
    public Team getMostFreeTeam() {
        return teams
                .values()
                .stream()
                .min(Comparator.comparingInt(value -> value.getPlayers().count()))
                .orElseThrow(() -> new IllegalStateException("No teams present"));
    }

    @Override
    public void addTeam(Team team) {
        if (this.teams.put(team.getName(), team) != null) {
            throw new IllegalArgumentException("Team already exists");
        }
    }

    @Override
    public Set<Team> getAllTeams() {
        return Collections.unmodifiableSet(new HashSet<>(teams.values()));
    }

    @Override
    public int getAliveTeams() {
        return (int) teams.values().stream().filter(Team::isAlive).count();
    }
}
