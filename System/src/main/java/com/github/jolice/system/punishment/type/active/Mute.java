package com.github.jolice.system.punishment.type.active;

import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.service.ActivePunishmentService;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import lombok.RequiredArgsConstructor;
import com.github.jolice.system.punishment.type.PunishResult;

import java.util.UUID;

@RequiredArgsConstructor
public class Mute implements ActivePunishment {

    private final ActivePunishmentService activePunishmentService;
    private final Excuse excuse;

    @Override
    public PunishResult punish(PunishmentParameters punishmentParameters) {
        return activePunishmentService.punish(punishmentParameters, ActivePunishmentType.MUTE);
    }

    @Override
    public void excuse(UUID uuid) {
        excuse.excuse(uuid, ActivePunishmentType.MUTE);
    }
}
