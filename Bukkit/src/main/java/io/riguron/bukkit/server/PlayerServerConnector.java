package io.riguron.bukkit.server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Class responsible for connecting players to BungeeCord servers.
 */
@RequiredArgsConstructor
public class PlayerServerConnector {

    private final Server server;
    private final Plugin plugin;

    public void connect(UUID id, String targetServer) {
        Player player = server.getPlayer(id);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(targetServer);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
