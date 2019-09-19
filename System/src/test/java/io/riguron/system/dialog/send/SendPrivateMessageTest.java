package io.riguron.system.dialog.send;

import io.riguron.system.punishment.model.ActivePunishmentRecord;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.dialog.MessageSendResponse;
import io.riguron.system.dialog.PrivateMessage;
import io.riguron.system.punishment.PunishmentChecker;
import io.riguron.system.punishment.model.ActivePunishmentRecord;
import io.riguron.system.punishment.model.ActivePunishmentType;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SendPrivateMessageTest {

    @Test
    public void whenMutedThenNotSent() {
        PunishmentChecker punishmentChecker = mock(PunishmentChecker.class);
        FindOnlinePlayer findOnlinePlayer = mock(FindOnlinePlayer.class);

        PrivateMessage privateMessage = new PrivateMessage(UUID.randomUUID(), "to", "text","fromName");

        when(punishmentChecker.checkPunishment(eq(privateMessage.getFrom()), eq(ActivePunishmentType.MUTE)))
                .thenReturn(Optional.of(mock(ActivePunishmentRecord.class)));

        SendPrivateMessage sendPrivateMessage = new SendPrivateMessage(punishmentChecker, findOnlinePlayer);
        MessageSendResponse response = sendPrivateMessage.send(privateMessage);
        assertEquals(MessageSendResponse.MUTED, response);
        verifyZeroInteractions(findOnlinePlayer);
    }

    @Test
    public void whenNotMutedThenSent() {
        PunishmentChecker punishmentChecker = mock(PunishmentChecker.class);
        FindOnlinePlayer findOnlinePlayer = mock(FindOnlinePlayer.class);

        PrivateMessage privateMessage = new PrivateMessage(UUID.randomUUID(), "to", "text","fromName");

        when(punishmentChecker.checkPunishment(any(), any())).thenReturn(Optional.empty());
        SendPrivateMessage sendPrivateMessage = new SendPrivateMessage(punishmentChecker, findOnlinePlayer);

        when(findOnlinePlayer.findAndSend(eq(privateMessage))).thenReturn(MessageSendResponse.OK);

        MessageSendResponse response = sendPrivateMessage.send(privateMessage);
        assertEquals(MessageSendResponse.OK, response);
        verify(findOnlinePlayer).findAndSend(eq(privateMessage));
    }
}