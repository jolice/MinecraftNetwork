package io.riguron.system.punishment;

import io.riguron.system.punishment.model.ActivePunishmentRecord;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import io.riguron.system.login.LoginDetails;
import io.riguron.system.login.chain.LoginProcessingException;
import io.riguron.system.punishment.model.ActivePunishmentRecord;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PunishmentLinkTest {

    @Test(expected = LoginProcessingException.class)
    public void whenBannedThenDisallowed() {

        PunishmentChecker checker = mock(PunishmentChecker.class);
        PunishmentDataRepository repository = mock(PunishmentDataRepository.class);

        PunishmentLink link = new PunishmentLink(checker, repository);

        UUID uuid = UUID.randomUUID();
        LoginDetails loginDetails = new LoginDetails(
                uuid,
                "target"
        );

        when(repository.findPunishmentData(eq(uuid))).thenReturn(
                Optional.of(new PunishmentData(uuid))
        );


        when(checker.checkPunishment(eq(uuid), eq(ActivePunishmentType.BAN)))
                .thenReturn(Optional.of(mock(ActivePunishmentRecord.class)));

        try {
            link.proceed(loginDetails);
        } finally {
            verify(repository).put(eq(uuid), any());
            verify(repository).invalidate(eq(uuid));
        }
    }

}