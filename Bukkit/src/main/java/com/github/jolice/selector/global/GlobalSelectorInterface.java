package com.github.jolice.selector.global;

import java.util.Map;

/**
 * Represents interface for selecting different game modes.
 * Global means that this selector enables player to pick the game type,
 * not the concrete server.
 */
public interface GlobalSelectorInterface {

    /**
     * Refreshes the visual interface.
     *
     * @param online mapping of the each game type to the total online players on that game mode.
     */
    void update(Map<String, Integer> online);

}
