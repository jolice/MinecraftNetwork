package io.riguron.bungee.listener.stream;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import io.riguron.server.ServerName;
import io.riguron.server.repository.PlayerRepository;
import io.riguron.system.task.TaskFactory;

/**
 * Class responsible for handling players' stream and updating
 * appropriate data in the PlayerRepository.
 */
@RequiredArgsConstructor
public class PlayerServerListener implements Listener {

    private final ServerName serverName;
    private final PlayerRepository playerRepository;
    private final TaskFactory taskFactory;

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        taskFactory
                .newAsyncTask(() -> playerRepository.setPlayerProxy(serverName.get(), e.getPlayer().getName()))
                .execute();
    }

    @EventHandler
    public void onConnect(ServerConnectedEvent event) {
        taskFactory
                .newAsyncTask(() -> playerRepository.setPlayerServer(event.getServer().getInfo().getName(), event.getPlayer().getName()))
                .execute();
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        taskFactory
                .newAsyncTask(() -> playerRepository.deletePlayerData(event.getPlayer().getName()))
                .execute();
    }

}
