package me.riguron.game.winner.solo.order;

import me.riguron.game.player.GamePlayer;

public class PlaceOrderByDropout implements PlaceOrderBy {

    @Override
    public Integer apply(GamePlayer gamePlayer) {
        return gamePlayer.getStatus().getDropOutTime();
    }
}
