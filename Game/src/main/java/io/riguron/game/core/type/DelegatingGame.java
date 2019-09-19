package io.riguron.game.core.type;

import io.riguron.game.core.Game;
import lombok.experimental.Delegate;
import io.riguron.game.core.Game;

public class DelegatingGame implements Game {

    @Delegate
    private Game delegate;

    public DelegatingGame(Game delegate) {
        this.delegate = delegate;
    }
}
