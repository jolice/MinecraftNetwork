package me.riguron.system.dialog.send;

import me.riguron.system.dialog.MessageSendResponse;
import me.riguron.system.dialog.PrivateMessage;
import me.riguron.system.player.PlayerAssociation;
import me.riguron.system.player.PlayerProfile;
import me.riguron.system.player.PlayerProfileRepository;
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