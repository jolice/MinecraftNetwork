package io.riguron.game.winner.solo.order;

import io.riguron.game.player.GamePlayer;

public class PlaceOrderByScore implements PlaceOrderBy {

    @Override
    public Integer apply(GamePlayer gamePlayer) {
        return gamePlayer.getScore().getValue();
    }
}
