package me.riguron.game.team;

import me.riguron.game.player.team.TeamPlayer;
import me.riguron.game.team.add.TeamAddResult;

import java.util.Set;

public interface TeamPlayers {

    Set<TeamPlayer> list();

    int count();

    int max();

    long alive();

    TeamAddResult add(TeamPlayer player);

    boolean remove(TeamPlayer gamePlayer);

}
