package io.riguron.bukkit.message;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import io.riguron.system.internalization.Message;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.stream.Broadcast;

@RequiredArgsConstructor
public class BukkitBroadcast implements Broadcast {

    private final Server server;
    private final SendMessage sendMessage;

    @Override
    public void broadcast(String message) {
        server.getOnlinePlayers().forEach((player -> player.sendMessage(message)));
    }

    @Override
    public void broadcast(Message message) {
        server.getOnlinePlayers().forEach(player -> sendMessage.to(player.getUniqueId(), message));
    }
}
