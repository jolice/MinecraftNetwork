package com.github.jolice.game.map;

import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Container storing all maps of the game.
 */
public class GameMaps {

    private NavigableMap<String, GameMap> gameMapStorage = new TreeMap<>();

    public GameMaps(List<GameMap> loadedMaps) {
        loadedMaps.forEach(gameMap -> gameMapStorage.put(gameMap.getName(), gameMap));
    }

    public Optional<GameMap> getMap(String key) {
        return Optional.ofNullable(gameMapStorage.get(key));
    }

    public GameMap getArbitraryMap() {
        return gameMapStorage.firstEntry().getValue();
    }
}
