package com.github.jolice.game.team;

import com.github.jolice.game.team.add.SuccessfulAdd;
import com.github.jolice.game.team.add.AlreadyInCurrentTeam;
import com.github.jolice.game.team.add.TeamAddResult;
import com.github.jolice.game.team.add.TeamIsFull;
import lombok.RequiredArgsConstructor;
import com.github.jolice.game.player.team.TeamPlayer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class DefaultTeamPlayers implements TeamPlayers {

    private final Set<TeamPlayer> players = new HashSet<>();
    private final int maxPlayerCount;
    private final Team team;

    @Override
    public Set<TeamPlayer> list() {
        return Collections.unmodifiableSet(players);
    }

    @Override
    public int count() {
        return players.size();
    }

    @Override
    public int max() {
        return maxPlayerCount;
    }

    @Override
    public long alive() {
        return players.isEmpty() ? 0 : players.stream().filter(x -> x.getStatus().isAlive()).count();
    }

    @Override
    public TeamAddResult add(TeamPlayer player) {

        if (team.isFull()) {
            return new TeamIsFull(team);
        }

        if (player.getTeam() != this && this.players.add(player)) {
            player.getTeam().getPlayers().remove(player);
            player.setTeam(team);
            return new SuccessfulAdd(team);
        }

        return new AlreadyInCurrentTeam(team);
    }

    @Override
    public boolean remove(TeamPlayer gamePlayer) {
        if (gamePlayer.getTeam() == this && this.players.remove(gamePlayer)) {
            gamePlayer.setTeam(NullTeam.INSTANCE);
            return true;
        }
        return false;
    }


}
