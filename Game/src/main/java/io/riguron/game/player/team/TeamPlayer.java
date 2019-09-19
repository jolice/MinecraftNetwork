package io.riguron.game.player.team;

import io.riguron.game.team.Team;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.team.Team;

public interface TeamPlayer extends GamePlayer {

    Team getTeam();

    void setTeam(Team team);

    boolean isInTeam();

    boolean isTeamMate(TeamPlayer other);

}
