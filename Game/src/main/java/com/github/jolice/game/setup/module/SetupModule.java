package com.github.jolice.game.setup.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.config.client.ConfigurationSaver;
import com.github.jolice.game.setup.MapSetup;
import com.github.jolice.game.setup.command.SaveCommand;
import com.github.jolice.game.setup.command.TeleportCommand;
import org.bukkit.Server;

public class SetupModule extends AbstractModule {

    @Provides
    @Singleton
    public SaveCommand saveCommand(MapSetup mapSetup, ConfigurationSaver configurationSaver) {
        return new SaveCommand(mapSetup, configurationSaver);
    }

    @Provides
    @Singleton
    public TeleportCommand teleportCommand(Server server) {
        return new TeleportCommand(server);
    }
}
