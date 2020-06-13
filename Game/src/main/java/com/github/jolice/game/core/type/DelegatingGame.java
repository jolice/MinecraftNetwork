package com.github.jolice.game.core.type;

import com.github.jolice.game.core.Game;
import lombok.experimental.Delegate;

public class DelegatingGame implements Game {

    @Delegate
    private Game delegate;

    public DelegatingGame(Game delegate) {
        this.delegate = delegate;
    }
}
