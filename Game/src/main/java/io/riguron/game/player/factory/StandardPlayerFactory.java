package io.riguron.game.player.factory;

import io.riguron.game.player.BasicGamePlayer;
import io.riguron.game.player.GamePlayer;
import io.riguron.game.player.GamePlayer;
import org.bukkit.entity.Player;
import io.riguron.game.player.BasicGamePlayer;

public class StandardPlayerFactory implements GamePlayerFactory<GamePlayer> {
    @Override
    public GamePlayer createPlayer(Player source) {
        return new BasicGamePlayer(source);
    }
}
