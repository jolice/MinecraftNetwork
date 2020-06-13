package com.github.jolice.system.dialog.send;

import com.github.jolice.system.dialog.MessageSendResponse;
import com.github.jolice.system.dialog.PrivateMessage;
import com.github.jolice.system.player.PlayerAssociation;
import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.player.PlayerProfileRepository;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FindPlayerProfileTest {

    @Test
    public void whenProfileFound() {

        PlayerProfileRepository repository = mock(PlayerProfileRepository.class);
        CheckTarget checkTarget = mock(CheckTarget.class);

        FindPlayerProfile findPlayerProfile = new FindPlayerProfile(
                repository, checkTarget
        );

        PrivateMessage privateMessage= new PrivateMessage(UUID.randomUUID(), "to", "text","fromName");

        PlayerProfile playerProfile = mock(PlayerProfile.class);
        when(repository.find(any(), (PlayerAssociation[]) any())).thenReturn(Optional.of(playerProfile));

        when(checkTarget.checkTargetAndSend(eq(playerProfile), eq(privateMessage), eq("proxy"))).thenReturn(MessageSendResponse.OK);

        findPlayerProfile.findProfileAndProceed(privateMessage, "proxy");
        verify(checkTarget).checkTargetAndSend(eq(playerProfile), eq(privateMessage), eq("proxy"));

    }
}