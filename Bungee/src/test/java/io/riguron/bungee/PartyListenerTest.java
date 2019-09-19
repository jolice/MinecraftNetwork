package io.riguron.bungee;

import io.riguron.bungee.listener.PartyListener;
import io.riguron.system.message.PlayerMessaging;
import io.riguron.system.party.PartyService;
import io.riguron.system.task.Task;
import io.riguron.system.task.TaskFactory;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PartyListenerTest {

    private static final String PARTY_OWNER = "owner";

    @Test
    public void connect() {

        PartyService partyService = mock(PartyService.class);
        PlayerMessaging playerMessaging = mock(PlayerMessaging.class);
        TaskFactory taskFactory = mock(TaskFactory.class);
        when(taskFactory.newAsyncTask(any())).thenAnswer(invocation -> (Task) () -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run();
        });
        ServerConnectEvent serverConnectEvent = mock(ServerConnectEvent.class);
        when(serverConnectEvent.getPlayer()).thenAnswer(invocation -> {
            ProxiedPlayer proxiedPlayer = mock(ProxiedPlayer.class);
            when(proxiedPlayer.getName()).thenReturn(PARTY_OWNER);
            return proxiedPlayer;
        });

        when(partyService.getInvitedMembers(eq(PARTY_OWNER))).thenReturn(new HashSet<>(
                Arrays.asList(
                        "a", "b", "c"
                )
        ));

        PartyListener partyListener = new PartyListener(partyService, playerMessaging, taskFactory);
        partyListener.connect(serverConnectEvent);

        verify(playerMessaging).distribute(
                eq(new HashSet<>((Arrays.asList("a", "b", "c")))), any()
        );
    }
}