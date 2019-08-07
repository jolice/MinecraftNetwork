package me.riguron.game.player.factory;

import me.riguron.game.player.GamePlayer;
import org.bukkit.entity.Player;
import me.riguron.game.player.GamePlayer;

public interface GamePlayerFactory<T extends GamePlayer> {

    T createPlayer(Player source);
}
