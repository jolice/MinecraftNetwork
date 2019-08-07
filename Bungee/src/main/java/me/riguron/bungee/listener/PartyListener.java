package me.riguron.bungee.listener;

import lombok.RequiredArgsConstructor;
import me.riguron.messaging.message.party.PartyTeleport;
import me.riguron.system.message.PlayerMessaging;
import me.riguron.system.party.PartyService;
import me.riguron.system.task.TaskFactory;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Party-related listener.
 */
@RequiredArgsConstructor
public class PartyListener implements Listener {

    private final PartyService partyService;
    private final PlayerMessaging playerMessaging;
    private final TaskFactory taskFactory;

    /**
     * Connects all party members to the server a player joined.
     *
     * @param event corresponding event
     */
    @EventHandler
    public void connect(ServerConnectEvent event) {
        taskFactory.newAsyncTask(() ->
                playerMessaging.distribute(
                        partyService
                                .getInvitedMembers(event.getPlayer().getName()),
                        s -> new PartyTeleport(s, event.getPlayer().getName())
                )).execute();
    }

    /**
     * Disbands party when player leaves.
     *
     * @param playerDisconnectEvent corresponding event
     */
    @EventHandler
    public void disconnect(PlayerDisconnectEvent playerDisconnectEvent) {
        taskFactory.newAsyncTask(() -> partyService.disband(playerDisconnectEvent.getPlayer().getName())).execute();
    }
}
