package me.riguron.game.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import me.riguron.game.core.Game;
import me.riguron.game.core.GameState;

@Value
@EqualsAndHashCode(callSuper = true)
public class GameStateChangeEvent extends GameEvent {

    private Game game;
    private GameState to;

}
