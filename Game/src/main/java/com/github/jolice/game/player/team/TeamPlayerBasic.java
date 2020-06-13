package com.github.jolice.game.player.team;

import com.github.jolice.game.team.NullTeam;
import com.github.jolice.game.team.Team;
import org.bukkit.entity.Player;
import com.github.jolice.game.player.BasicGamePlayer;

public class TeamPlayerBasic extends BasicGamePlayer implements TeamPlayer {

    private Team team = NullTeam.INSTANCE;

    public TeamPlayerBasic(Player player) {
        super(player);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isInTeam() {
        return this.team != NullTeam.INSTANCE;
    }

    public boolean isTeamMate(TeamPlayer other) {
        return other.getTeam().equals(this.team);
    }
}
