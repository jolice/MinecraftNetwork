package com.github.jolice.game.map.type;

import com.github.jolice.game.map.GameMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractGameMap implements GameMap {

    private final String name;

    @Override
    public String getName() {
        return name;
    }

}
