package me.riguron.game;

import me.riguron.game.bootstrap.GameProvider;
import me.riguron.system.plugin.PluginConfiguration;

public interface GameBootstrapper {

    GameProvider gameProvider();

    PluginConfiguration gameConfiguration();
}
