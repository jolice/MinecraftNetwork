package io.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import io.riguron.server.ServerProfile;
import io.riguron.server.repository.OnlineIndexingRepository;
import io.riguron.system.task.TaskFactory;

/**
 * Class responsible for indexing server in the global online repository.
 */
@RequiredArgsConstructor
public class OnlineIndexer {

    private final ServerProfile serverProfile;
    private final TaskFactory taskFactory;
    private final OnlineIndexingRepository onlineIndexingRepository;

    /**
     * Increments online at current server
     */
    public void increment() {
        taskFactory.newAsyncTask(() -> onlineIndexingRepository.incrementOnline(serverProfile.getServerGroup(), serverProfile.getServerName())).execute();
    }

    /**
     * Decrements online at current server
     */
    public void decrement() {
        taskFactory.newAsyncTask(() -> onlineIndexingRepository.decrementOnline(serverProfile.getServerGroup(), serverProfile.getServerName())).execute();
    }
}
