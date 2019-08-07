package me.riguron.game.winner.solo.order;

import me.riguron.game.player.GamePlayer;

import java.util.function.Function;

public interface PlaceOrderBy extends Function<GamePlayer, Integer> {

    Integer apply(GamePlayer gamePlayer);

}
