package com.github.jolice.game.player.factory;

import com.github.jolice.game.player.GamePlayer;
import org.bukkit.entity.Player;

public interface GamePlayerFactory<T extends GamePlayer> {

    T createPlayer(Player source);
}
