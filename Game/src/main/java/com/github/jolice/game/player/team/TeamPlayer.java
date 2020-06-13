package com.github.jolice.game.player.team;

import com.github.jolice.game.team.Team;
import com.github.jolice.game.player.GamePlayer;

public interface TeamPlayer extends GamePlayer {

    Team getTeam();

    void setTeam(Team team);

    boolean isInTeam();

    boolean isTeamMate(TeamPlayer other);

}
