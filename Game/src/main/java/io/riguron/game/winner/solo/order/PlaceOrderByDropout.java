package io.riguron.game.winner.solo.order;

import io.riguron.game.player.GamePlayer;

public class PlaceOrderByDropout implements PlaceOrderBy {

    @Override
    public Integer apply(GamePlayer gamePlayer) {
        return gamePlayer.getStatus().getDropOutTime();
    }
}
