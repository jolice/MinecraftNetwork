package com.github.jolice.system.chat;

import com.github.jolice.system.rank.Rank;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.player.PlayerProfile;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatFormatterTest {

    @Test
    public void getChatMessage() {
        PlayerProfileRepository repository = mock(PlayerProfileRepository.class);
        UUID uuid = UUID.randomUUID();
        PlayerProfile profile = mock(PlayerProfile.class);
        when(profile.getName()).thenReturn("riguron");
        when(profile.getRank()).thenAnswer(invocation -> {
            Rank rank = mock(Rank.class);
            when(rank.getPrefix()).thenReturn("admin");
            return rank;
        });


        when(repository.get(eq(uuid))).thenReturn(profile);

        ChatFormatter chatFormatter = new ChatFormatter(repository);

        String message = chatFormatter.getChatMessage(uuid, "Message");
        assertEquals("[admin] riguron: Message", message);

    }
}