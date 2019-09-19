package io.riguron.game.player.team;

import io.riguron.game.player.NullPlayer;
import io.riguron.game.team.NullTeam;
import io.riguron.game.team.Team;
import io.riguron.game.player.NullPlayer;
import io.riguron.game.team.NullTeam;
import io.riguron.game.team.Team;

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
