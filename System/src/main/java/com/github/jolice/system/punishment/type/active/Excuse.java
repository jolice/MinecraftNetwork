package com.github.jolice.system.punishment.type.active;

import com.github.jolice.system.punishment.repository.PunishmentDataProvider;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.model.PunishmentData;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Class responsible for removing active punishments from the player.
 */
@RequiredArgsConstructor
public class Excuse {

    private final PunishmentDataProvider punishmentDataProvider;
    private final PunishmentDataRepository dataRepository;

    public void excuse(UUID uuid, ActivePunishmentType punishmentType) {
        PunishmentData punishments = punishmentDataProvider.getPunishmentsFor(uuid);
        punishments.removePunishment(punishmentType);
        dataRepository.update(punishments);
    }
}
