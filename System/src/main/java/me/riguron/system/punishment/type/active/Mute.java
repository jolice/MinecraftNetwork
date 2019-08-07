package me.riguron.system.punishment.type.active;

import lombok.RequiredArgsConstructor;
import me.riguron.system.punishment.model.ActivePunishmentType;
import me.riguron.system.punishment.param.PunishmentParameters;
import me.riguron.system.punishment.service.ActivePunishmentService;
import me.riguron.system.punishment.type.PunishResult;

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
