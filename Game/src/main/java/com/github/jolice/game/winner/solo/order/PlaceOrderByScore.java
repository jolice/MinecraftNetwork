package com.github.jolice.game.winner.solo.order;

import com.github.jolice.game.player.GamePlayer;

public class PlaceOrderByScore implements PlaceOrderBy {

    @Override
    public Integer apply(GamePlayer gamePlayer) {
        return gamePlayer.getScore().getValue();
    }
}
