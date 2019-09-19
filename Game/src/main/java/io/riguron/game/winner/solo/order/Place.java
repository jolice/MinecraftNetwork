package io.riguron.game.winner.solo.order;

import lombok.Value;
import io.riguron.game.player.GamePlayer;

@Value
public class Place {

    private int index;
    private GamePlayer gamePlayer;
}
