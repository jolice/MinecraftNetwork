package com.github.jolice.game.winner.solo.order;

import com.github.jolice.game.player.GamePlayer;

import java.util.function.Function;

public interface PlaceOrderBy extends Function<GamePlayer, Integer> {

    Integer apply(GamePlayer gamePlayer);

}
