package com.github.jolice.selector;

import java.util.HashMap;
import java.util.Map;

/**
 * Virtual storage for the selectors.
 */
public class SelectorRepository {

    private Map<String, ServerSelector<?>> selectors = new HashMap<>();

    /**
     * Adds selector to the storage.
     *
     * @param channel  name of the selector channel
     * @param selector selector
     */
    public void add(String channel, ServerSelector<?> selector) {
        selectors.put(channel, selector);
    }

    /**
     * Retrieves selector from the storage.
     *
     * @param name name of the channel
     * @return selector associated with provided name
     */
    public ServerSelector<?> getSelector(String name) {
        return selectors.get(name);
    }
}
