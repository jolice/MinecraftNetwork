package me.riguron.game.core.type;

import lombok.experimental.Delegate;
import me.riguron.game.core.Game;

public class DelegatingGame implements Game {

    @Delegate
    private Game delegate;

    public DelegatingGame(Game delegate) {
        this.delegate = delegate;
    }
}
