package io.riguron.game.map.type;

import io.riguron.game.map.GameMap;
import lombok.RequiredArgsConstructor;
import io.riguron.game.map.GameMap;

@RequiredArgsConstructor
public abstract class AbstractGameMap implements GameMap {

    private final String name;

    @Override
    public String getName() {
        return name;
    }

}
