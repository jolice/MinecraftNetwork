package io.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import io.riguron.server.ServerProfile;
import io.riguron.server.repository.OnlineIndexingRepository;
import io.riguron.system.task.startup.PostLoadTask;

/**
 * Task responsible for registering server at the online indexing repository.
 */
@RequiredArgsConstructor
public class ServerIndexingRegistration implements PostLoadTask {

    private final OnlineIndexingRepository repository;
    private final ServerProfile serverProfile;

    @Override
    public void run() {
        repository.addServer(
                serverProfile.getServerGroup(),
                serverProfile.getServerName()
        );
    }
}
