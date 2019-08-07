package me.riguron.game.map.type;

import lombok.RequiredArgsConstructor;
import me.riguron.game.map.GameMap;

@RequiredArgsConstructor
public abstract class AbstractGameMap implements GameMap {

    private final String name;

    @Override
    public String getName() {
        return name;
    }

}
