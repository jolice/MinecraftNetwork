package io.riguron.bukkit.selector.factory;

import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.selector.SelectorRepository;
import io.riguron.bukkit.selector.ServerItemProvider;
import io.riguron.bukkit.selector.ServerKind;
import io.riguron.bukkit.selector.ServerSelector;
import io.riguron.server.type.Server;
import io.riguron.system.server.ServerChannel;

/**
 * Decorator for the SelectorFactory that puts each channel to the storage.
 *
 * @param <I> type of visual item
 */
@RequiredArgsConstructor
public class StoringSelectorFactory<I> implements SelectorFactory<I> {

    private final SelectorRepository selectorRepository;
    private final SelectorFactory<I> delegate;

    @Override
    public <T extends Server> ServerSelector<T> newServerSelector(ServerChannel<T> serverChannel, ServerKind kind, ServerItemProvider<I, T> itemProvider) {
        ServerSelector<T> serverSelector = delegate.newServerSelector(serverChannel, kind, itemProvider);
        selectorRepository.add(serverChannel.getGroup(), serverSelector);
        return serverSelector;
    }
}
