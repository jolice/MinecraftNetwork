package com.github.jolice.system.punishment.service;

import com.github.jolice.system.punishment.model.type.PunishmentType;
import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.repository.PunishmentRepository;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.player.specification.PlayerNameSpecification;
import com.github.jolice.system.punishment.model.PunishmentRecord;
import lombok.RequiredArgsConstructor;
import com.github.jolice.system.punishment.type.PunishResult;
import com.github.jolice.system.punishment.type.PunishResultType;

@RequiredArgsConstructor
public class PunishmentService {

    private final PunishmentRepository punishmentRepository;
    private final PlayerProfileRepository playerProfileRepository;

    /**
     * Records a player punishment in a database, without applying any active punishments.
     *
     * @param punishmentParameters parameters of a punishment
     * @param punishmentType       type of the punishment
     * @return result of the punishment
     */
    public PunishResult punish(PunishmentParameters punishmentParameters, PunishmentType punishmentType) {
        return playerProfileRepository.find(new PlayerNameSpecification(punishmentParameters.getTargetName()))
                .map(playerProfile -> {
                    PunishmentRecord punishmentRecord = new PunishmentRecord(punishmentParameters.getPunisher(), playerProfile, punishmentParameters.getReason(), punishmentType);
                    punishmentRepository.savePunishment(punishmentRecord);
                    return new PunishResult(punishmentRecord, PunishResultType.OK);
                }).orElse(PunishResult.NOT_FOUND);
    }
}
