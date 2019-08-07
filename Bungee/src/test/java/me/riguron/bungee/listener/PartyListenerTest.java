package me.riguron.bungee.listener;

import me.riguron.system.message.PlayerMessaging;
import me.riguron.system.party.PartyService;
import me.riguron.system.task.Task;
import me.riguron.system.task.TaskFactory;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PartyListenerTest {

    @Test
    public void whenConnectThenAllJoined() {

        TaskFactory taskFactory = mock(TaskFactory.class);
        when(taskFactory.newAsyncTask(any())).thenAnswer((Answer<Task>) invocation -> () -> {
            Runnable r = invocation.getArgument(0);
            r.run();
        });

        PlayerMessaging playerMessaging = mock(PlayerMessaging.class);
        PartyService partyService = mock(PartyService.class);

        when(partyService.getInvitedMembers(eq("owner"))).thenReturn(
                new TreeSet<>(Arrays.asList("1", "2", "3"))
        );

        ServerConnectEvent event = mock(ServerConnectEvent.class);
        when(event.getPlayer()).thenAnswer(invocation -> {
            ProxiedPlayer proxiedPlayer = mock(ProxiedPlayer.class);
            when(proxiedPlayer.getName()).thenReturn("owner");
            return proxiedPlayer;
        });

        PartyListener listener = new PartyListener(partyService, playerMessaging, taskFactory);
        listener.connect(event);

        verify(playerMessaging).distribute(
                eq(new TreeSet<>(Arrays.asList("1", "2", "3"))),
                any()
        );

    }

    @Test
    public void disconnect() {
    }
}