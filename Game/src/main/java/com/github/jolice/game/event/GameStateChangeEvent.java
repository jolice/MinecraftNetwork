package com.github.jolice.game.event;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class GameStateChangeEvent extends GameEvent {

    private Game game;
    private GameState to;

}
