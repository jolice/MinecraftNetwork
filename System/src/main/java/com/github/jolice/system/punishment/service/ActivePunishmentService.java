package com.github.jolice.system.punishment.service;

import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.repository.PunishmentDataProvider;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.punishment.repository.PunishmentRepository;
import com.github.jolice.system.player.PlayerProfile;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.player.specification.PlayerNameSpecification;
import com.github.jolice.system.punishment.model.ActivePunishmentRecord;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.punishment.type.PunishResult;
import com.github.jolice.system.punishment.type.PunishResultType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Class responsible for active punishments.
 */
@RequiredArgsConstructor
public class ActivePunishmentService {

    private final PunishmentDataProvider punishmentDataProvider;
    private final PunishmentDataRepository punishmentDataRepository;
    private final PunishmentRepository punishmentRepository;
    private final PlayerProfileRepository playerProfileRepository;

    /**
     * Punishes a player.
     *
     * @param punishmentParameters details of a punishment
     * @param activePunishmentType type of active punishment (either ban or mute)
     * @return result of the punishment
     */
    public PunishResult punish(PunishmentParameters punishmentParameters, ActivePunishmentType activePunishmentType) {
        return playerProfileRepository.find(new PlayerNameSpecification(punishmentParameters.getTargetName()))
                .map(playerProfile -> {
                    PunishmentData punishmentData = punishmentDataProvider.getPunishmentsFor(playerProfile.getUuid());
                    ActivePunishmentRecord activePunishmentRecord = this.newPunishmentRecord(punishmentParameters, playerProfile, activePunishmentType);
                    this.addActiveRecord(punishmentData, activePunishmentRecord, activePunishmentType);
                    return new PunishResult(activePunishmentRecord, PunishResultType.OK);
                })
                .orElse(PunishResult.NOT_FOUND);
    }

    private void addActiveRecord(PunishmentData punishmentData, ActivePunishmentRecord punishmentRecord, ActivePunishmentType activePunishmentType) {
        punishmentData.addPunishment(activePunishmentType, punishmentRecord);
        punishmentRepository.savePunishment(punishmentRecord);
        punishmentDataRepository.update(punishmentData);
    }

    private ActivePunishmentRecord newPunishmentRecord(PunishmentParameters punishmentParameters, PlayerProfile playerProfile, ActivePunishmentType activePunishmentType) {
        return new ActivePunishmentRecord(
                punishmentParameters.getPunisher(), playerProfile, punishmentParameters.getReason(),
                LocalDateTime.now().plus(punishmentParameters.getDuration()),
                activePunishmentType);
    }


}
