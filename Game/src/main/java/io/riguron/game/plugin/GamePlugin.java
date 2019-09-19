package io.riguron.game.plugin;

import io.riguron.game.GameBootstrapper;
import lombok.extern.slf4j.Slf4j;
import io.riguron.bukkit.plugin.ServerPlugin;
import io.riguron.game.GameBootstrapper;
import io.riguron.system.plugin.PluginConfiguration;

import java.util.Arrays;

@Slf4j
public class GamePlugin extends ServerPlugin {

    @Override
    protected final PluginConfiguration configuration() {
        GameBootstrapper gameBootstrapper = findGamePlugin();
        return new GamePluginConfiguration(gameBootstrapper.gameProvider(), gameBootstrapper.gameConfiguration());
    }

    private GameBootstrapper findGamePlugin() {
        return
                (GameBootstrapper) Arrays
                        .stream(getServer().getPluginManager().getPlugins())
                        .filter(plugin -> !plugin.getName().equals("Game") && plugin.getName().startsWith("Game"))
                        .peek(plugin -> log.info("Found game implementation: " + plugin.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No game implementation found!"));

    }

}
