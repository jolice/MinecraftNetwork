package com.github.jolice.game.winner.solo.order;

import lombok.Value;
import com.github.jolice.game.player.GamePlayer;

@Value
public class Place {

    private int index;
    private GamePlayer gamePlayer;
}
