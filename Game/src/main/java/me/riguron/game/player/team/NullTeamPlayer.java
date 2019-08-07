package me.riguron.game.player.team;

import me.riguron.game.player.NullPlayer;
import me.riguron.game.team.NullTeam;
import me.riguron.game.team.Team;

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
