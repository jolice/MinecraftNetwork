package io.riguron.bukkit.selector.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator that provides the slot indexes for game server icons.
 */
public class GameSlotIterator implements Iterator<Integer> {

    private int i = 1;
    private int j = 0;

    @Override
    public boolean hasNext() {
        return currentCell() != 41;
    }

    @Override
    public Integer next() {
        this.ensureHasNext();
        do {
            if (j == 7) {
                i++;
                j = 1;
            } else {
                j++;
            }
        } while (!isTargetCell(i, j));
        return currentCell();
    }

    private boolean isTargetCell(int i, int j) {
        return i == 1 && (j == 1 || j == 7)
                || i == 2 && (j <= 2 || j >= 6)
                || i > 2;
    }

    private void ensureHasNext() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
    }

    private int currentCell() {
        return (i * 9) + j;
    }

}
