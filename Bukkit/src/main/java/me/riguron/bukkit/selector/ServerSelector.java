package me.riguron.bukkit.selector;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Represents visual server selector (an interface that enables
 * players to pick different servers to join).
 *
 * @param <T> type of the server
 */
public interface ServerSelector<T> {

    /**
     * Updates selector state by providing actual state
     * of servers being monitored by the current selector.
     *
     * @param servers fresh server data
     */
    void refresh(List<T> servers);

    /**
     * Displays the visual part of the selector to the player.
     *
     * @param player player who sees the interface
     */
    void displayTo(Player player);

}
