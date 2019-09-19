package io.riguron.game.team;

import io.riguron.game.team.add.TeamAddResult;
import io.riguron.game.player.team.TeamPlayer;
import io.riguron.game.team.add.TeamAddResult;

import java.util.Set;

public interface TeamPlayers {

    Set<TeamPlayer> list();

    int count();

    int max();

    long alive();

    TeamAddResult add(TeamPlayer player);

    boolean remove(TeamPlayer gamePlayer);

}
