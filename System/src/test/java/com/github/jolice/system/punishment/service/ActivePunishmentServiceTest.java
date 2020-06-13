package com.github.jolice.system.punishment.service;

import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.player.specification.PlayerNameSpecification;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.punishment.model.PunishmentRecord;
import com.github.jolice.system.punishment.model.type.PunishmentType;
import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.repository.PunishmentDataProvider;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.punishment.repository.PunishmentRepository;
import com.github.jolice.system.punishment.type.PunishResult;
import com.github.jolice.system.punishment.type.PunishResultType;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivePunishmentServiceTest {

    @Test
    public void punish() {
        PunishmentDataProvider provider = mock(PunishmentDataProvider.class);
        PunishmentDataRepository dataRepository = mock(PunishmentDataRepository.class);
        PunishmentRepository repository = mock(PunishmentRepository.class);
        PlayerProfileRepository profileRepository = mock(PlayerProfileRepository.class);

        PlayerProfile profile = mock(PlayerProfile.class);
        UUID uuid = UUID.randomUUID();
        when(profile.getUuid()).thenReturn(uuid);

        when(profileRepository.find(eq(new PlayerNameSpecification("target"))))
                .thenReturn(Optional.of(profile));

        when(provider.getPunishmentsFor(eq(uuid))).thenReturn(
              mock(PunishmentData.class)
        );

        ActivePunishmentService service = new ActivePunishmentService(
                provider,
                dataRepository,
                repository,
                profileRepository
        );

        PunishmentParameters punishmentParameters = new PunishmentParameters(
                "target",
                "console",
                "Reason",
                Duration.of(10, ChronoUnit.DAYS)
        );

        final PunishResult punish = service.punish(
                punishmentParameters,
                ActivePunishmentType.MUTE
        );

        Assert.assertEquals(PunishResultType.OK, punish.getResultType());
        PunishmentRecord punishmentRecord = punish.getPunishmentRecord();
        assertSame(profile, punishmentRecord.getTarget());
        Assert.assertEquals(PunishmentType.MUTE, punishmentRecord.getPunishmentType());
        assertEquals("console", punishmentRecord.getPunisher());
    }
}