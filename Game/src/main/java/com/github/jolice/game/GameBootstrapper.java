package com.github.jolice.game;

import com.github.jolice.game.bootstrap.GameProvider;
import io.riguron.system.plugin.PluginConfiguration;

public interface GameBootstrapper {

    GameProvider gameProvider();

    PluginConfiguration gameConfiguration();
}
