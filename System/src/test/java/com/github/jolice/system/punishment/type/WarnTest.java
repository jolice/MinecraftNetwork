package com.github.jolice.system.punishment.type;

import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.punishment.model.PunishmentRecord;
import com.github.jolice.system.punishment.model.type.PunishmentType;
import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.repository.PunishmentDataProvider;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.punishment.service.PunishmentService;
import com.github.jolice.system.punishment.type.active.Ban;
import org.junit.Test;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public class WarnTest {

    private final PunishmentDataProvider dataProvider = mock(PunishmentDataProvider.class);;
    private final UUID playerId = UUID.randomUUID();

    @Test
    public void punish() {

        PunishmentService service = mock(PunishmentService.class);

        PunishmentDataRepository dataRepository = mock(PunishmentDataRepository.class);
        Ban ban = mock(Ban.class);

        Warn warn = new Warn(
                service,
                dataProvider,
                dataRepository,
                ban,
                3
        );

        PunishmentRecord record = mock(PunishmentRecord.class);
        when(record.getTarget()).thenAnswer(invocation -> {
            PlayerProfile profile = mock(PlayerProfile.class);
            when(profile.getUuid()).thenReturn(playerId);
            return profile;
        });

        PunishmentParameters parameters = new PunishmentParameters(
                "target",
                "punisher",
                "reason",
                Duration.ofDays(10)
        );

        when(service.punish(eq(parameters), eq(PunishmentType.WARN))).thenReturn(
                new PunishResult(
                        record,
                        PunishResultType.OK
                )
        );



      Runnable punish = () -> warn.punish(parameters);

        this.warn(1, punishmentData -> verify(punishmentData).setWarns(eq(2)), punish);
        this.warn(3, punishmentData -> {
            verify(ban).punish(same(parameters));
            verify(punishmentData).setWarns(eq(0));
        }, punish);

    }

    private void warn(int currentWarns, Consumer<PunishmentData> assertion, Runnable punish) {
        PunishmentData data = mock(PunishmentData.class);
        when(data.getWarns()).thenReturn(currentWarns);
        when(dataProvider.getPunishmentsFor(eq(playerId)))
                .thenReturn(data);
        punish.run();
        assertion.accept(data);

    }

}