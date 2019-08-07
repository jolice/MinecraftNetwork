package me.riguron.bukkit.selector.factory;

import me.riguron.bukkit.selector.ServerItemProvider;
import me.riguron.bukkit.selector.ServerKind;
import me.riguron.bukkit.selector.ServerSelector;
import me.riguron.server.type.Server;
import me.riguron.system.server.ServerChannel;

public interface SelectorFactory<I> {

    /**
     * Constructs the server selector.
     *
     * @param serverChannel appropriate ServerChannel
     * @param kind          type of the server
     * @param itemProvider  function that maps the server data to the visual element
     * @param <T>           type of the server
     * @return selector instance
     */
    <T extends Server> ServerSelector<T> newServerSelector(ServerChannel<T> serverChannel, ServerKind kind, ServerItemProvider<I, T> itemProvider);

}
