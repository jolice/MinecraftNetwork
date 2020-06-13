package com.github.jolice.game.core.type;

import com.github.jolice.game.core.Game;
import com.github.jolice.game.core.GameState;
import com.github.jolice.game.event.GameStartEvent;
import com.github.jolice.game.event.GameStateChangeEvent;
import com.github.jolice.game.map.GameMap;
import org.bukkit.plugin.PluginManager;

/**
 * Main game implementation.
 */
public class AbstractGame implements Game {

    private GameState gameState = GameState.WAITING;
    private GameMap map;
    private PluginManager pluginManager;

    public AbstractGame(GameMap map, PluginManager pluginManager) {
        this.setMap(map);
        this.pluginManager = pluginManager;
    }

    @Override
    public void start() {
        pluginManager.callEvent(new GameStartEvent());
    }

    @Override
    public GameState getState() {
        return gameState;
    }

    @Override
    public void end() {
        this.setState(GameState.END);
    }

    @Override
    public GameMap getMap() {
        return this.map;
    }

    @Override
    public void setMap(GameMap map) {
        this.map = map;
    }

    @Override
    public void setState(GameState newState) {
        if (newState != this.gameState) {
            this.gameState = newState;
            pluginManager.callEvent(new GameStateChangeEvent(this, newState));
        }
    }
}
