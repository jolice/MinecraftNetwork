package me.riguron.bukkit.selector.factory;

import lombok.RequiredArgsConstructor;
import me.riguron.bukkit.selector.SelectorRepository;
import me.riguron.bukkit.selector.ServerItemProvider;
import me.riguron.bukkit.selector.ServerKind;
import me.riguron.bukkit.selector.ServerSelector;
import me.riguron.server.type.Server;
import me.riguron.system.server.ServerChannel;

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
