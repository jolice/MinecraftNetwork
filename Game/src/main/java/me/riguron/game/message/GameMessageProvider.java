package me.riguron.game.message;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import me.riguron.bukkit.stream.StreamMessageProvider;
import me.riguron.bukkit.stream.StreamType;
import me.riguron.game.config.GameOptions;
import me.riguron.system.internalization.Message;

import java.util.UUID;

@RequiredArgsConstructor
public class GameMessageProvider implements StreamMessageProvider {

    private final GameOptions gameOptions;
    private final Server server;

    @Override
    public Message getStreamMessage(StreamType streamType, UUID joinedPlayer) {
        return new Message(String.format("game.%s.broadcast",
                streamType.getDescription()),
                server.getPlayer(joinedPlayer).getName(),
                server.getOnlinePlayers(),
                gameOptions.getMinimumPlayersToStart()
        );
    }
}
