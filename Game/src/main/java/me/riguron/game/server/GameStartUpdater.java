package me.riguron.game.server;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.riguron.game.event.GameStartEvent;
import me.riguron.server.ServerProfile;
import me.riguron.server.repository.OnlineIndexingRepository;
import me.riguron.server.repository.ServerFieldType;
import me.riguron.server.repository.ServerRepository;
import me.riguron.system.task.TaskFactory;

/**
 * Removes server from online indexing after the game starts.
 */
@RequiredArgsConstructor
public class GameStartUpdater implements Listener {

    private final OnlineIndexingRepository repository;
    private final ServerProfile serverProfile;
    private final ServerRepository serverRepository;
    private final TaskFactory taskFactory;

    @EventHandler
    public void gameStarted(GameStartEvent event) {
        taskFactory.newAsyncTask(() -> {
            repository.removeServerData(serverProfile.getServerGroup(), serverProfile.getServerName());
            serverRepository.set(serverProfile.getServerName(), ServerFieldType.ACTIVE, true);
        }).execute();
    }
}
