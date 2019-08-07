package me.riguron.game.core.type;

import org.bukkit.plugin.PluginManager;
import me.riguron.game.core.Game;
import me.riguron.game.core.GameState;
import me.riguron.game.event.GameStartEvent;
import me.riguron.game.event.GameStateChangeEvent;
import me.riguron.game.map.GameMap;

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
