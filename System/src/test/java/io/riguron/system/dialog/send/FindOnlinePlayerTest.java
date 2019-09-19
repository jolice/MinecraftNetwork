package io.riguron.system.dialog.send;

import io.riguron.server.repository.PlayerRepository;
import io.riguron.system.dialog.MessageSendResponse;
import io.riguron.system.dialog.PrivateMessage;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class FindOnlinePlayerTest {

    @Test
    public void whenPlayerOnlineThenSent() {

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        FindPlayerProfile findPlayerProfile = mock(FindPlayerProfile.class);

        when(playerRepository.getPlayerProxy(eq("player"))).thenReturn(
                Optional.of("proxy-1")
        );

        FindOnlinePlayer findOnlinePlayer = new FindOnlinePlayer(playerRepository, findPlayerProfile);
        PrivateMessage privateMessage = new PrivateMessage(
                UUID.randomUUID(),
                "player",
                "text","fromName"
        );

        when(findPlayerProfile.findProfileAndProceed(eq(privateMessage), eq("proxy-1"))).thenReturn(
                MessageSendResponse.OK
        );

        assertEquals(MessageSendResponse.OK, findOnlinePlayer.findAndSend(privateMessage));
    }

    @Test
    public void whenPlayerNotOnlineThenNotSent() {
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        FindPlayerProfile findPlayerProfile = mock(FindPlayerProfile.class);

        when(playerRepository.getPlayerProxy(any())).thenReturn(Optional.empty());

        FindOnlinePlayer findOnlinePlayer = new FindOnlinePlayer(playerRepository, findPlayerProfile);
        PrivateMessage privateMessage = new PrivateMessage(
                UUID.randomUUID(),
                "player",
                "text","fromName"
        );
       assertEquals(MessageSendResponse.OFFLINE, findOnlinePlayer.findAndSend(privateMessage));
       verifyZeroInteractions(findPlayerProfile);
    }
}