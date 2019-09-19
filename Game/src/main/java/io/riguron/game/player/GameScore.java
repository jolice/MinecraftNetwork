package io.riguron.game.player;

import lombok.Data;

/**
 * Score holder.
 */
@Data
public class GameScore implements Comparable<GameScore> {

    private int value;

    public void increment() {
        this.value++;
    }

    @Override
    public int compareTo(GameScore o) {
        return Integer.compare(getValue(), o.getValue());
    }
}
