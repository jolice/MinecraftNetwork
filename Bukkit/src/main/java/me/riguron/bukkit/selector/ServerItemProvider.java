package me.riguron.bukkit.selector;

import me.riguron.server.type.Server;

/**
 * Maps server information to the appropriate visual display
 * element (sign, item stack or whatever it is).
 *
 * @param <I> type of the visual element
 * @param <T> type of the server
 */
public interface ServerItemProvider<I, T extends Server> {

    /**
     * Function that maps server object to the visual element.
     *
     * @param gameServer server
     * @return constructed visual element
     */
    I createServerItem(T gameServer);

}
