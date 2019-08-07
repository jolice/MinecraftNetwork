package me.riguron.system.punishment.type;

import lombok.RequiredArgsConstructor;
import me.riguron.system.punishment.model.type.PunishmentType;
import me.riguron.system.punishment.param.PunishmentParameters;
import me.riguron.system.punishment.service.PunishmentService;

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
