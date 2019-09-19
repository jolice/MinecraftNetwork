package io.riguron.system.punishment.service;

import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;
import io.riguron.system.punishment.model.ActivePunishmentRecord;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import lombok.RequiredArgsConstructor;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.player.specification.PlayerNameSpecification;
import io.riguron.system.punishment.repository.PunishmentDataProvider;
import io.riguron.system.punishment.model.ActivePunishmentRecord;
import io.riguron.system.punishment.model.ActivePunishmentType;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import io.riguron.system.punishment.repository.PunishmentRepository;
import io.riguron.system.punishment.type.PunishResult;
import io.riguron.system.punishment.type.PunishResultType;

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
