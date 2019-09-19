package io.riguron.system.punishment.type.active;

import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentRecord;
import io.riguron.system.punishment.service.ActivePunishmentService;
import io.riguron.system.punishment.type.ProxyKick;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentRecord;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.service.ActivePunishmentService;
import io.riguron.system.punishment.type.ProxyKick;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BanTest {

    @Test
    public void punish() {
        ActivePunishmentService service = mock(ActivePunishmentService.class);
        Excuse excuse = mock(Excuse.class);
        ProxyKick proxyKick = mock(ProxyKick.class);

        Ban ban = new Ban(
                service, excuse, proxyKick
        );

        PunishmentParameters punishmentParameters = new PunishmentParameters(
                "target",
                "punisher",
                "reason",
                Duration.ofDays(10)
        );

        PunishResult returnResult = new PunishResult(
                mock(PunishmentRecord.class),
                PunishResultType.OK
        );

        when(service.punish(eq(punishmentParameters), eq(ActivePunishmentType.BAN)))
                .thenReturn(returnResult);

        PunishResult result = ban.punish(
                punishmentParameters
        );

        assertSame(returnResult, result);
        verify(proxyKick).kick(eq("target"), eq("reason"));


    }

}