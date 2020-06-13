package com.github.jolice.system.punishment;

import com.github.jolice.system.punishment.model.ActivePunishmentRecord;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.task.TaskFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PunishmentChecker {

    private final PunishmentDataRepository punishmentDataRepository;
    private final TaskFactory taskFactory;

    /**
     * Checks whether the player has an active punishment. Also checks for the
     * punishment expiration.
     *
     * @param uuid                 player's ID
     * @param activePunishmentType type of the punishment
     * @return optional wrapping active punishment record of the specified type, or empty optional if there is no such a record
     */
    public Optional<ActivePunishmentRecord> checkPunishment(UUID uuid, ActivePunishmentType activePunishmentType) {
        return punishmentDataRepository.getPunishmentData(uuid)
                .flatMap(punishmentData -> {
                    Optional<ActivePunishmentRecord> record = punishmentData.getPunishment(activePunishmentType);
                    return record.flatMap(activePunishmentRecord -> {
                        if (activePunishmentRecord.isExpired()) {
                            punishmentData.removePunishment(activePunishmentType);
                            taskFactory
                                    .newAsyncTask(() -> punishmentDataRepository.update(punishmentData))
                                    .execute();
                            return Optional.empty();
                        } else {
                            return record;
                        }
                    });
                });
    }

}