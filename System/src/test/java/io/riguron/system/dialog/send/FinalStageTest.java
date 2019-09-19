package io.riguron.system.dialog.send;

import io.riguron.messaging.MessagingService;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.dialog.MessageSendResponse;
import io.riguron.system.dialog.PrivateMessage;
import io.riguron.system.dialog.PrivateMessageRepository;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
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

        assertEquals(
                MessageSendResponse.OK,
                finalStage.sendFinalStage(
                        targetProfile, privateMessage, "proxy")
        );

    }
}