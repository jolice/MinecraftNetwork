package com.github.jolice.game.player.factory;

import com.github.jolice.game.player.BasicGamePlayer;
import com.github.jolice.game.player.GamePlayer;
import org.bukkit.entity.Player;

public class StandardPlayerFactory implements GamePlayerFactory<GamePlayer> {
    @Override
    public GamePlayer createPlayer(Player source) {
        return new BasicGamePlayer(source);
    }
}
