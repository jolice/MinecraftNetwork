package me.riguron.bukkit.selector.iterator;

import java.util.Iterator;

/**
 * Iterator that provides the slot indexes for lobby server icons.
 */
public class LobbySlotIterator implements Iterator<Integer> {

    private static final int START_SLOT = 28;
    private static final int END_SLOT = 34;

    private int cursor = START_SLOT;

    @Override
    public boolean hasNext() {
        return cursor != END_SLOT;
    }

    @Override
    public Integer next() {
        return cursor++;
    }
}
