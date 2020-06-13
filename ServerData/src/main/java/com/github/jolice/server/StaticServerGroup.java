package com.github.jolice.server;

import lombok.RequiredArgsConstructor;

/**
 * Plain ServerGroup implementation that just holds static group
 * value and doesn't perform any conversion logic.
 */
@RequiredArgsConstructor
public class StaticServerGroup implements ServerGroup {

    private final String name;

    @Override
    public String getName() {
        return name;
    }
}
