package io.riguron.system.punishment.service;

import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;
import io.riguron.system.punishment.model.PunishmentRecord;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;
import io.riguron.system.punishment.model.PunishmentRecord;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.repository.PunishmentRepository;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PunishmentServiceTest {

    @Test
    public void punish() {
        PunishmentRepository repository = mock(PunishmentRepository.class);
        PlayerProfileRepository profileRepository = mock(PlayerProfileRepository.class);

        PunishmentService service = new PunishmentService(
                repository, profileRepository
        );

        PunishmentParameters parameters = new PunishmentParameters(
                "target",
                "punisher",
                "reason",
                Duration.of(10, ChronoUnit.HOURS)
        );

        PlayerProfile profile = mock(PlayerProfile.class);

        when(profileRepository.find(eq(new PlayerNameSpecification("target"))))
                .thenReturn(Optional.ofNullable(profile));

        PunishResult result = service.punish(parameters, PunishmentType.KICK);

        Assert.assertEquals(PunishResultType.OK, result.getResultType());
        assertNotNull(result.getPunishmentRecord());

        PunishmentRecord punishmentRecord = result.getPunishmentRecord();
        assertSame(profile, punishmentRecord.getTarget());
        assertEquals("punisher", punishmentRecord.getPunisher());
        Assert.assertEquals(PunishmentType.KICK, punishmentRecord.getPunishmentType());


    }
}