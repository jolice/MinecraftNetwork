package com.github.jolice.system.dialog.send;

import com.github.jolice.messaging.MessagingService;
import com.github.jolice.system.dialog.MessageSendResponse;
import com.github.jolice.system.dialog.PrivateMessage;
import com.github.jolice.system.dialog.PrivateMessageRepository;
import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.player.PlayerProfileRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinalStageTest {

    @Test
    public void sendFinalStage() {

        MessagingService messagingService = mock(MessagingService.class);
        PlayerProfileRepository repository = mock(PlayerProfileRepository.class);
        PrivateMessageRepository pmRepository = mock(PrivateMessageRepository.class);

        FinalStage finalStage = new FinalStage(
                messagingService, repository, pmRepository);

        PlayerProfile targetProfile = mock(PlayerProfile.class);
        PrivateMessage privateMessage = new PrivateMessage(
                UUID.randomUUID(), "to", "text","fromName"
        );

        when(repository.get(eq(privateMessage.getFrom()))).thenReturn(targetProfile);

        Assert.assertEquals(
                MessageSendResponse.OK,
                finalStage.sendFinalStage(
                        targetProfile, privateMessage, "proxy")
        );

    }
}