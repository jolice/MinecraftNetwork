package io.riguron.game.message;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import io.riguron.bukkit.stream.StreamMessageProvider;
import io.riguron.bukkit.stream.StreamType;
import io.riguron.game.config.GameOptions;
import io.riguron.system.internalization.Message;

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
