package io.riguron.system.punishment.model;

import io.riguron.system.player.PlayerProfile;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;


public class ActivePunishmentRecordTest {

    @Test
    public void whenDateIsManyYearsAheadThenNotExpired() {

        LocalDateTime expiration = LocalDateTime.of(2100, Month.JANUARY, 5, 17, 50, 0);

        ActivePunishmentRecord activePunishmentRecord = new ActivePunishmentRecord(
                "punisher",
                mock(PlayerProfile.class),
                "reason",
                expiration,
                ActivePunishmentType.MUTE
        );

        assertFalse(activePunishmentRecord.isExpired());
    }


}