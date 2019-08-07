package me.riguron.bukkit.punish;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.riguron.system.punishment.PunishmentChecker;
import me.riguron.system.punishment.model.ActivePunishmentType;

import java.util.UUID;

@RequiredArgsConstructor
public class PunishChatListener implements Listener {

    private final PunishmentChecker punishmentChecker;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        punishmentChecker.checkPunishment(uuid, ActivePunishmentType.MUTE).ifPresent(activePunishmentRecord -> {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You are muted, reason - " + activePunishmentRecord.getReason());
        });
    }
}
