package io.riguron.game.player.factory;

import io.riguron.game.player.GamePlayer;
import org.bukkit.entity.Player;
import io.riguron.game.player.GamePlayer;

public interface GamePlayerFactory<T extends GamePlayer> {

    T createPlayer(Player source);
}
