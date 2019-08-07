package me.riguron.bukkit.server;

import lombok.RequiredArgsConstructor;
import me.riguron.server.ServerProfile;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.system.task.startup.PostLoadTask;

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
