package com.github.jolice.message;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import io.riguron.system.internalization.Message;
import io.riguron.system.internalization.InternalizationService;
import io.riguron.system.internalization.SendMessage;
import io.riguron.system.player.PlayerProfileRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class PlayerSendMessage implements SendMessage {

    private final PlayerProfileRepository playerRepository;
    private final InternalizationService internalizationService;
    private final Server server;

    @Override
    public void to(UUID id, Message message) {
        server.getPlayer(id).sendMessage(
                internalizationService.getMessage(playerRepository.get(id), message));
    }

    @Override
    public void to(UUID uuid, String message) {
        server.getPlayer(uuid).sendMessage(message);
    }
}
