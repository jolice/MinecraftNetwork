package me.riguron.system.punishment.type.active;

import lombok.RequiredArgsConstructor;
import me.riguron.system.punishment.model.ActivePunishmentType;
import me.riguron.system.punishment.model.PunishmentData;
import me.riguron.system.punishment.repository.PunishmentDataProvider;
import me.riguron.system.punishment.repository.PunishmentDataRepository;

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
