package com.github.jolice.game.player.team;

import com.github.jolice.game.team.NullTeam;
import com.github.jolice.game.team.Team;
import com.github.jolice.game.player.NullPlayer;

/**
 * Null-object (special case) pattern.
 */
public class NullTeamPlayer extends NullPlayer implements TeamPlayer {

    private Team team = NullTeam.INSTANCE;

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void setTeam(Team team) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInTeam() {
        return false;
    }

    @Override
    public boolean isTeamMate(TeamPlayer other) {
        return false;
    }
}
