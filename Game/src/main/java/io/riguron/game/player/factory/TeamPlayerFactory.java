package io.riguron.game.player.factory;

import io.riguron.game.player.team.TeamPlayer;
import io.riguron.game.player.team.TeamPlayerBasic;
import io.riguron.game.player.team.TeamPlayer;
import org.bukkit.entity.Player;
import io.riguron.game.player.team.TeamPlayerBasic;

public class TeamPlayerFactory implements GamePlayerFactory<TeamPlayer> {

    @Override
    public TeamPlayer createPlayer(Player source) {
        return new TeamPlayerBasic(source);
    }
}
