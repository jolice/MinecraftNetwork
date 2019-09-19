package io.riguron.game.core.type;

import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import io.riguron.game.event.GameStartEvent;
import io.riguron.game.event.GameStateChangeEvent;
import io.riguron.game.map.GameMap;
import org.bukkit.plugin.PluginManager;
import io.riguron.game.core.Game;
import io.riguron.game.core.GameState;
import io.riguron.game.event.GameStartEvent;
import io.riguron.game.event.GameStateChangeEvent;
import io.riguron.game.map.GameMap;

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
