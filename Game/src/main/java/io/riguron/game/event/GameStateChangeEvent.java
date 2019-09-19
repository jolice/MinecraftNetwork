package io.riguron.game.event;

import io.riguron.game.core.Game;
import lombok.EqualsAndHashCode;
import lombok.Value;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;

@Value
@EqualsAndHashCode(callSuper = true)
public class GameStateChangeEvent extends GameEvent {

    private Game game;
    private GameState to;

}
