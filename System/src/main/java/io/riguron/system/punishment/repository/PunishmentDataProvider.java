package io.riguron.system.punishment.repository;

import io.riguron.system.punishment.model.PunishmentData;
import lombok.RequiredArgsConstructor;
import io.riguron.system.punishment.model.PunishmentData;

import java.util.UUID;

/**
 * This class is responsible for creating PunishmentData record for a player lazily,
 * on short notice. This is useful because it does not make sense to store empty punishment
 * data for each player, since only a little percent of players has been ever punished.
 */
@RequiredArgsConstructor
public class PunishmentDataProvider {

    private final PunishmentDataRepository punishmentDataRepository;

    /**
     * Returns PunishmentData for the player with specified ID, or creates new record
     * if there is no existing one.
     *
     * @param uuid id of punishment target
     * @return PunishmentData associated with a specified player
     */
    public PunishmentData getPunishmentsFor(UUID uuid) {
        return punishmentDataRepository
                .findPunishmentData(uuid)
                .orElseGet(() -> {
                    PunishmentData punishmentData = new PunishmentData(uuid);
                    punishmentDataRepository.save(punishmentData);
                    return punishmentData;
                });
    }

}
