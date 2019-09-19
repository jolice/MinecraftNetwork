package io.riguron.system.punishment.service;

import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.model.PunishmentRecord;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.repository.PunishmentDataProvider;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.model.PunishmentRecord;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.repository.PunishmentDataProvider;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import io.riguron.system.punishment.repository.PunishmentRepository;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;
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