package io.riguron.game;

import io.riguron.game.bootstrap.GameProvider;
import io.riguron.system.plugin.PluginConfiguration;

public interface GameBootstrapper {

    GameProvider gameProvider();

    PluginConfiguration gameConfiguration();
}
