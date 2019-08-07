package me.riguron.game.player.factory;

import me.riguron.game.player.GamePlayer;
import org.bukkit.entity.Player;
import me.riguron.game.player.BasicGamePlayer;

public class StandardPlayerFactory implements GamePlayerFactory<GamePlayer> {
    @Override
    public GamePlayer createPlayer(Player source) {
        return new BasicGamePlayer(source);
    }
}
