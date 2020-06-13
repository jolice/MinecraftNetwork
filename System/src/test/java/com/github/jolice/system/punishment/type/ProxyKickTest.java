package com.github.jolice.system.punishment.type;

import com.github.jolice.messaging.MessagingService;
import com.github.jolice.messaging.message.KickProxyCommand;
import com.github.jolice.server.repository.PlayerRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProxyKickTest {

    @Test
    public void kick() {

        PlayerRepository repository = mock(PlayerRepository.class);
        MessagingService messagingService = mock(MessagingService.class);

        ProxyKick proxyKick = new ProxyKick(
                repository, messagingService
        );

        when(repository.getPlayerProxy(eq("riguron")))
                .thenReturn(Optional.of("proxy"));


        assertTrue(proxyKick.kick("riguron", "reason for kick"));

        verify(messagingService).send(eq(new KickProxyCommand("riguron", "reason for kick"))
                , eq("proxy"));

    }
}