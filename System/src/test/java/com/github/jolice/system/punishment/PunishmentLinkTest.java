package com.github.jolice.system.punishment;

import com.github.jolice.system.login.LoginDetails;
import com.github.jolice.system.login.chain.LoginProcessingException;
import com.github.jolice.system.punishment.model.ActivePunishmentRecord;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
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