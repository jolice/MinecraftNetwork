package io.riguron.game.core;

import io.riguron.game.map.GameMap;
import io.riguron.game.map.GameMap;

public interface Game {

    void start();

    GameState getState();

    void end();

    void setState(GameState gameState);

    GameMap getMap();

    void setMap(GameMap map);



}
