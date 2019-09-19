package io.riguron.bukkit.selector;

import lombok.RequiredArgsConstructor;
import io.riguron.bukkit.gui.Executable;
import io.riguron.bukkit.server.PlayerServerConnector;
import io.riguron.system.server.ServerChannel;

/**
 * Utility methods for the selector interface.
 */
@RequiredArgsConstructor
public class ServerConnectionActions {

    private final ServerChannel<?> serverChannel;
    private final PlayerServerConnector playerServerConnector;

    /**
     * Executable that connects the clicked player to the most
     * suitable server in the current channel.
     *
     * @return button for instant server connection.
     */
    public Executable getFastServerItem() {
        return playerId -> playerServerConnector.connect(playerId, serverChannel.getMostSuitableServer());
    }

    /**
     * Executable that connects the clicked player to the server
     * that is associated with this item.
     *
     * @param serverName name of the server
     * @return button for concrete server connection.
     */
    public Executable getServerConnectItem(String serverName) {
        return playerId -> playerServerConnector.connect(playerId, serverName);
    }
}
