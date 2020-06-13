package com.github.jolice.selector.factory;

import com.github.jolice.selector.ServerItemProvider;
import com.github.jolice.selector.ServerKind;
import com.github.jolice.selector.ServerSelector;
import io.riguron.server.type.Server;
import io.riguron.system.server.ServerChannel;

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
