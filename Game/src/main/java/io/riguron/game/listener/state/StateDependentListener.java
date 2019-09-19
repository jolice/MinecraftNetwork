package io.riguron.game.listener.state;

import org.bukkit.event.Listener;
import io.riguron.game.core.GameState;

import java.util.List;

/**
 * Represents a listener that is active only while game is in a certain list
 * of states.
 */
public interface StateDependentListener extends Listener {

    /**
     * List of game states while this listener is active.
     *
     * @return states of the current listener
     */
    List<GameState> states();

}
