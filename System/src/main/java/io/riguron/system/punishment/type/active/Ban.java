package io.riguron.system.punishment.type.active;

import io.riguron.system.punishment.model.ActivePunishmentType;
import lombok.RequiredArgsConstructor;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.service.ActivePunishmentService;
import io.riguron.system.punishment.type.ProxyKick;
import io.riguron.system.punishment.type.PunishResult;

import java.util.UUID;

@RequiredArgsConstructor
public class Ban implements ActivePunishment {

    private final ActivePunishmentService punishmentService;
    private final Excuse excuse;
    private final ProxyKick proxyKick;

    @Override
    public PunishResult punish(PunishmentParameters punishmentParameters) {
        return punishmentService
                .punish(punishmentParameters, ActivePunishmentType.BAN)
                .ifPresent(record -> proxyKick.kick(
                        punishmentParameters.getTargetName(),
                        punishmentParameters.getReason()
                ));
    }

    @Override
    public void excuse(UUID uuid) {
        excuse.excuse(uuid, ActivePunishmentType.BAN);
    }
}
