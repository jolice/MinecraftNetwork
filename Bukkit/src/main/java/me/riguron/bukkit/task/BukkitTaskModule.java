package me.riguron.bukkit.task;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.TimerFactory;

public class BukkitTaskModule extends AbstractModule {

    @Provides
    @Singleton
    public TaskFactory taskFactory(Plugin plugin, BukkitScheduler bukkitScheduler) {
        return new BukkitTaskFactory(plugin, bukkitScheduler);
    }

    @Provides
    @Singleton
    public TimerFactory timerFactory(Plugin plugin, BukkitScheduler bukkitScheduler) {
        return new BukkitTimerFactory(plugin, bukkitScheduler);
    }
}
