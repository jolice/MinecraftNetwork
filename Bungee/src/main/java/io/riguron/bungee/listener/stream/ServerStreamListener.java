package io.riguron.bungee.listener.stream;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import io.riguron.server.ConvertingServerGroup;
import io.riguron.server.ServerGroup;
import io.riguron.server.repository.GlobalOnlineRepository;
import io.riguron.server.repository.ServerRepository;
import io.riguron.system.task.TaskFactory;

/**
 * Listener responsible for handling players' joins and leaves and
 * sending corresponding updates to the server repositories.
 */
@RequiredArgsConstructor
public class ServerStreamListener implements Listener {

    private final GlobalOnlineRepository globalOnlineRepository;
    private final ServerRepository serverRepository;
    private final TaskFactory taskFactory;

    @EventHandler
    public void onLogin(ServerConnectedEvent serverConnectedEvent) {
        taskFactory.newAsyncTask(() -> {
            String name = serverConnectedEvent.getServer().getInfo().getName();
            ServerGroup group = new ConvertingServerGroup(name);
            globalOnlineRepository.increment(group);
            serverRepository.incrementOnline(name);
        }).execute();
    }

    @EventHandler
    public void onQuit(ServerDisconnectEvent event) {
        taskFactory.newAsyncTask(() -> {
            String name = event.getTarget().getName();
            ServerGroup group = new ConvertingServerGroup(name);
            serverRepository.decrementOnline(name);
            globalOnlineRepository.decrement(group);
        }).execute();
    }

}

