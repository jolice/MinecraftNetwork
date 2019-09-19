package io.riguron.game.winner.solo.order;

import io.riguron.game.player.GamePlayer;

import java.util.function.Function;

public interface PlaceOrderBy extends Function<GamePlayer, Integer> {

    Integer apply(GamePlayer gamePlayer);

}
