package com.github.jolice.system.punishment.type;

import com.github.jolice.system.punishment.model.type.PunishmentType;
import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.service.PunishmentService;
import lombok.RequiredArgsConstructor;

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
