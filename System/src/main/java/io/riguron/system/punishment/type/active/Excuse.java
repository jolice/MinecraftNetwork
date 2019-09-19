package io.riguron.system.punishment.type.active;

import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.repository.PunishmentDataProvider;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import lombok.RequiredArgsConstructor;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.repository.PunishmentDataProvider;
import io.riguron.system.punishment.repository.PunishmentDataRepository;

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
