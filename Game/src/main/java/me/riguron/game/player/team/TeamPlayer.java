package me.riguron.game.player.team;

import me.riguron.game.player.GamePlayer;
import me.riguron.game.team.Team;

public interface TeamPlayer extends GamePlayer {

    Team getTeam();

    void setTeam(Team team);

    boolean isInTeam();

    boolean isTeamMate(TeamPlayer other);

}
