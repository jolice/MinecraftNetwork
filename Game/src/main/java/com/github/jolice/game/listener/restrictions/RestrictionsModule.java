package com.github.jolice.game.listener.restrictions;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.github.jolice.game.config.advanced.AdvancedGameOptions;
import com.github.jolice.game.listener.state.StateDependentListener;
import io.riguron.system.task.TaskFactory;
import io.riguron.system.task.startup.PostLoadTask;
import org.bukkit.Server;

public class RestrictionsModule extends AbstractModule {

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener blockListener(AdvancedGameOptions advancedGameOptions) {
        return new BlockListener(advancedGameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener chaneListener(AdvancedGameOptions advancedGameOptions) {
        return new ChangeListener(advancedGameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener clickListener(AdvancedGameOptions advancedGameOptions) {
        return new ClickListener(advancedGameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener damageListener(AdvancedGameOptions advancedGameOptions) {
        return new DamageListener(advancedGameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener itemListener(AdvancedGameOptions advancedGameOptions) {
        return new ItemListener(advancedGameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public StateDependentListener spawnListener(AdvancedGameOptions advancedGameOptions) {
        return new SpawnListener(advancedGameOptions);
    }

    @ProvidesIntoSet
    @Singleton
    public PostLoadTask timeChangeRunner(AdvancedGameOptions advancedGameOptions, Server server, TaskFactory taskFactory) {
        return new TimeChangeRunner(advancedGameOptions, taskFactory, server);
    }


}
