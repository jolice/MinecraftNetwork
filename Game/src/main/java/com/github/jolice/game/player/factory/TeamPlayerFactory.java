package com.github.jolice.game.player.factory;

import com.github.jolice.game.player.team.TeamPlayer;
import com.github.jolice.game.player.team.TeamPlayerBasic;
import org.bukkit.entity.Player;

public class TeamPlayerFactory implements GamePlayerFactory<TeamPlayer> {

    @Override
    public TeamPlayer createPlayer(Player source) {
        return new TeamPlayerBasic(source);
    }
}
