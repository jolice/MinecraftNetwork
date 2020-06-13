package com.github.jolice.selector.global;

import lombok.RequiredArgsConstructor;
import io.riguron.server.repository.GlobalOnlineRepository;
import io.riguron.system.task.FixedRepeatingAction;
import io.riguron.system.task.TaskFactory;

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
