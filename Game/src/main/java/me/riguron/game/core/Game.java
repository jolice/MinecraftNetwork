package me.riguron.game.core;

import me.riguron.game.map.GameMap;

public interface Game {

    void start();

    GameState getState();

    void end();

    void setState(GameState gameState);

    GameMap getMap();

    void setMap(GameMap map);



}
