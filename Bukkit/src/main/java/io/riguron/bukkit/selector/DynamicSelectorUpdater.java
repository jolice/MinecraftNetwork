package io.riguron.bukkit.selector;

import lombok.RequiredArgsConstructor;
import io.riguron.server.type.Server;
import io.riguron.system.server.ServerChannel;

import java.util.List;
import java.util.function.Supplier;

/**
 * Task responsible for updating server selectors.
 *
 * @param <T> type of the server.
 */
@RequiredArgsConstructor
public class DynamicSelectorUpdater<T extends Server> implements Supplier<List<T>> {

    private final ServerSelector<T> serverSelector;
    private final ServerChannel<T> serverChannel;

    @Override
    public List<T> get() {
        return serverChannel.poll();
    }

    public void update(List<T> list) {
        serverSelector.refresh(list);
    }
}
