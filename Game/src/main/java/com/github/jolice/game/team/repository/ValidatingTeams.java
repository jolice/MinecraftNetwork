package com.github.jolice.game.team.repository;

import lombok.RequiredArgsConstructor;
import com.github.jolice.game.team.NullTeam;
import com.github.jolice.game.team.Team;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class ValidatingTeams implements Teams {

    private final Teams delegate;

    @Override
    public Optional<Team> getByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Team name can not be null or empty");
        }
        return delegate.getByName(name);
    }

    @Override
    public Set<String> getTeamNames() {
        return delegate.getTeamNames();
    }

    @Override
    public Team getMostFreeTeam() {
        return delegate.getMostFreeTeam();
    }

    @Override
    public void addTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team can not be null");
        }
        if (team == NullTeam.INSTANCE) {
            throw new IllegalArgumentException("Attempting to put special-case object");
        }
        delegate.addTeam(team);
    }

    @Override
    public Set<Team> getAllTeams() {
        return delegate.getAllTeams();
    }

    @Override
    public int getAliveTeams() {
        return delegate.getAliveTeams();
    }
}
