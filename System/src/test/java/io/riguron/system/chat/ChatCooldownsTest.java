package io.riguron.system.chat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, ChatCooldowns.class})
public class ChatCooldownsTest {

    @Before
    public void mockSystem() {
        mockStatic(System.class);
    }

    @Test
    public void whenNewThenNotInCooldown() {
        UUID uuid = UUID.randomUUID();
        ChatCooldowns chatCooldowns = new ChatCooldowns(3);
        assertFalse(chatCooldowns.checkCooldown(uuid).isPresent());
    }

    @Test
    public void whenAddedToCooldownThenReturnedDifference() {
        UUID uuid = UUID.randomUUID();
        when(System.currentTimeMillis()).thenReturn(5000L);
        ChatCooldowns chatCooldowns = new ChatCooldowns(10);
        chatCooldowns.onChat(uuid);

        when(System.currentTimeMillis()).thenReturn(7000L);

        Optional<Integer> cooldownData = chatCooldowns.checkCooldown(uuid);
        assertTrue(cooldownData.isPresent());
        assertEquals(8, cooldownData.get());
    }

    @Test
    public void removePlayer() {

        ChatCooldowns chatCooldowns = new ChatCooldowns(100);
        UUID uuid = UUID.randomUUID();
        chatCooldowns.onChat(uuid);
        assertTrue(chatCooldowns.checkCooldown(uuid).isPresent());
        chatCooldowns.removePlayer(uuid);
        assertFalse(chatCooldowns.checkCooldown(uuid).isPresent());
    }

}