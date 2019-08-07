package me.riguron.game.winner.solo.order;

import lombok.Value;
import me.riguron.game.player.GamePlayer;

@Value
public class Place {

    private int index;
    private GamePlayer gamePlayer;
}
