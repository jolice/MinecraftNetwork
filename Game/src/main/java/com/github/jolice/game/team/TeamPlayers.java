package com.github.jolice.game.team;

import com.github.jolice.game.team.add.TeamAddResult;
import com.github.jolice.game.player.team.TeamPlayer;

import java.util.Set;

public interface TeamPlayers {

    Set<TeamPlayer> list();

    int count();

    int max();

    long alive();

    TeamAddResult add(TeamPlayer player);

    boolean remove(TeamPlayer gamePlayer);

}
