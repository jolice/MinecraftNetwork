package io.riguron.bukkit.listener;

import lombok.RequiredArgsConstructor;
import io.riguron.command.base.SenderKind;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import io.riguron.command.engine.CommandFrontController;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.UUID;

/**
 * Bukkit command listener. Delegates command execution to the command
 * framework.
 */
@RequiredArgsConstructor
public class BukkitCommandListener implements Listener {

    private static final UUID CONSOLE_UUID = UUID.randomUUID();

    private final CommandFrontController commandFrontController;

    /**
     * Player command handler.
     *
     * @param event corresponding event
     */
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (commandFrontController.onIncomingCommand(event.getMessage().substring(1), event.getPlayer().getUniqueId(), SenderKind.PLAYER)) {
            event.setCancelled(true);
        }
    }

    /**
     * Server command handler.
     *
     * @param event corresponding event
     */
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (commandFrontController.onIncomingCommand(event.getCommand(), CONSOLE_UUID, SenderKind.CONSOLE)) {
            event.setCancelled(true);
        }
    }


}
