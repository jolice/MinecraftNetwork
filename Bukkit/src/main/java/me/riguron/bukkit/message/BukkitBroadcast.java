package me.riguron.bukkit.message;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.SendMessage;
import me.riguron.system.stream.Broadcast;

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
