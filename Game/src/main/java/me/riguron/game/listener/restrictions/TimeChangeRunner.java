package me.riguron.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import me.riguron.game.config.advanced.AdvancedGameOptions;
import me.riguron.system.task.TaskFactory;
import me.riguron.system.task.startup.PostLoadTask;
import org.bukkit.Server;

@RequiredArgsConstructor
public class TimeChangeRunner implements PostLoadTask {

    private final AdvancedGameOptions advancedGameOptions;
    private final TaskFactory taskFactory;
    private final Server server;

    @Override
    public void run() {
        if (advancedGameOptions.getChangingOptions().isTimeChange()) {
            taskFactory.newRepeatingTask(() -> server.getWorlds().forEach(world -> world.setTime(0)), 10);
        }
    }
}
