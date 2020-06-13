package com.github.jolice.server.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents query for the list of servers with minimum players online (instant join).
 */
@RequiredArgsConstructor
@Getter
public class FreeServersQueryOptions {

    /**
     * Minimum count of servers that must be returned
     */
    private final int min;

    /**
     * Maximum count of servers that must be returned
     */
    private final int max;

    /**
     * Count of servers that must be returned
     */
    private final int count;

    public FreeServersQueryOptions(int max, int count) {
        this(1, max, count);
    }


}
