package me.riguron.game.player.factory;

import me.riguron.game.player.team.TeamPlayer;
import org.bukkit.entity.Player;
import me.riguron.game.player.team.TeamPlayerBasic;

public class TeamPlayerFactory implements GamePlayerFactory<TeamPlayer> {

    @Override
    public TeamPlayer createPlayer(Player source) {
        return new TeamPlayerBasic(source);
    }
}
