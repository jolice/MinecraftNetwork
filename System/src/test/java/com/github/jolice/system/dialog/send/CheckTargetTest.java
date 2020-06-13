package com.github.jolice.system.dialog.send;

import com.github.jolice.system.dialog.MessageSendResponse;
import com.github.jolice.system.dialog.PrivateMessage;
import com.github.jolice.system.dialog.ignore.IgnoreRepository;
import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.preferences.PlayerPreferences;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckTargetTest {

    @Test
    public void whenTargetIsIgnoring() {

        IgnoreRepository ignoreRepository = mock(IgnoreRepository.class);
        CheckTarget checkTarget = new CheckTarget(ignoreRepository, mock(FinalStage.class));

        PlayerProfile targetProfile = mock(PlayerProfile.class);
        UUID targetProfileUUID = UUID.randomUUID();
        when(targetProfile.getUuid()).thenReturn(targetProfileUUID);
        PrivateMessage privateMessage = new PrivateMessage(UUID.randomUUID(),
                "to", "text", "fromName");

        when(ignoreRepository.isIgnoring(eq(targetProfileUUID), eq(privateMessage.getFromName()))).thenReturn(true);

        Assert.assertEquals(MessageSendResponse.IGNORING, checkTarget.checkTargetAndSend(targetProfile, privateMessage, "proxy"));
    }

    @Test
    public void whenTargetHasDisabled() {

        IgnoreRepository ignoreRepository = mock(IgnoreRepository.class);
        CheckTarget checkTarget = new CheckTarget(ignoreRepository, mock(FinalStage.class));

        PlayerProfile targetProfile = mock(PlayerProfile.class);
        UUID targetProfileUUID = UUID.randomUUID();
        when(targetProfile.getUuid()).thenReturn(targetProfileUUID);
        PrivateMessage privateMessage = new PrivateMessage(UUID.randomUUID(),
                "to", "text", "fromName");

        when(ignoreRepository.isIgnoring(any(), any())).thenReturn(false);
        when(targetProfile.getPlayerPreferences()).thenAnswer((Answer<PlayerPreferences>) invocation -> {
            PlayerPreferences playerPreferences = mock(PlayerPreferences.class);
            when(playerPreferences.isPrivateMessages()).thenReturn(false);
            return playerPreferences;
        });

        Assert.assertEquals(MessageSendResponse.DISABLED ,checkTarget.checkTargetAndSend(
                targetProfile,
                privateMessage,
                "proxy"
        ));

    }

}