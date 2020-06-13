package com.github.jolice.listener;

import lombok.RequiredArgsConstructor;
import io.riguron.system.chat.ChatCooldowns;
import io.riguron.system.internalization.Message;
import io.riguron.system.internalization.SendMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import io.riguron.system.chat.ChatFormatter;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class PlayerChatListener implements Listener {

    private final ChatFormatter chatFormatter;
    private final ChatCooldowns cooldowns;
    private final SendMessage sendMessage;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        Optional<Integer> cooldown = cooldowns.checkCooldown(uuid);
        if (cooldown.isPresent()) {
            sendMessage.to(uuid, new Message("chat.cooldown.message", cooldown.get()));
            event.setCancelled(true);
        } else {
            event.setFormat(chatFormatter.getChatMessage(uuid, event.getMessage()));
            cooldowns.onChat(uuid);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent quitEvent) {
        cooldowns.removePlayer(quitEvent.getPlayer().getUniqueId());
    }


}
