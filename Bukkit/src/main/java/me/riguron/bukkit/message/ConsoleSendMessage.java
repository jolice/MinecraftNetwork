package me.riguron.bukkit.message;

import lombok.RequiredArgsConstructor;
import me.riguron.system.internalization.Message;
import me.riguron.system.internalization.InternalizationService;
import me.riguron.system.internalization.SendMessage;
import org.bukkit.Server;

import java.util.Locale;
import java.util.UUID;

@RequiredArgsConstructor
public class ConsoleSendMessage implements SendMessage {

    private final Server server;
    private final InternalizationService internalizationService;

    @Override
    public void to(UUID uuid, Message message) {
        server.getConsoleSender().sendMessage(internalizationService.getMessage(() -> Locale.ENGLISH, message));
    }

    @Override
    public void to(UUID uuid, String message) {
        server.getConsoleSender().sendMessage(message);
    }
}
