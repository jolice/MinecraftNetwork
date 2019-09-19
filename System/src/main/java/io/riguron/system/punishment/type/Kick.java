package io.riguron.system.punishment.type;

import io.riguron.system.punishment.model.type.PunishmentType;
import lombok.RequiredArgsConstructor;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.service.PunishmentService;

@RequiredArgsConstructor
public class Kick implements Punishment {

    private final ProxyKick proxyKick;
    private final PunishmentService punishmentService;

    @Override
    public PunishResult punish(PunishmentParameters punishmentParameters) {
        return proxyKick.kick(punishmentParameters.getTargetName(), punishmentParameters.getReason()) ?
                punishmentService
                        .punish(punishmentParameters, PunishmentType.KICK) : PunishResult.NOT_FOUND;
    }

}
