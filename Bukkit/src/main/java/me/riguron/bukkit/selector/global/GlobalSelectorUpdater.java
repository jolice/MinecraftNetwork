package me.riguron.bukkit.selector.global;

import lombok.RequiredArgsConstructor;
import me.riguron.server.repository.GlobalOnlineRepository;
import me.riguron.system.task.FixedRepeatingAction;
import me.riguron.system.task.TaskFactory;

@RequiredArgsConstructor
public class GlobalSelectorUpdater implements FixedRepeatingAction {

    private final GlobalOnlineRepository globalOnlineRepository;
    private final GlobalSelectorInterface globalSelectorInterface;
    private final TaskFactory taskFactory;

    @Override
    public void run() {
        taskFactory
                .newAsyncFunction(globalOnlineRepository::getGroupsOnline)
                .completeSynchronously(globalSelectorInterface::update);
    }

    @Override
    public int getInterval() {
        return 1;
    }
}
