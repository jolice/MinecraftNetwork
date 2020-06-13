package com.github.jolice.game.core;

import com.github.jolice.game.map.GameMap;

public interface Game {

    void start();

    GameState getState();

    void end();

    void setState(GameState gameState);

    GameMap getMap();

    void setMap(GameMap map);



}
