package me.riguron.system.punishment.service;

import lombok.RequiredArgsConstructor;
import me.riguron.system.player.PlayerProfileRepository;
import me.riguron.system.player.specification.PlayerNameSpecification;
import me.riguron.system.punishment.model.PunishmentRecord;
import me.riguron.system.punishment.model.type.PunishmentType;
import me.riguron.system.punishment.param.PunishmentParameters;
import me.riguron.system.punishment.repository.PunishmentRepository;
import me.riguron.system.punishment.type.PunishResult;
import me.riguron.system.punishment.type.PunishResultType;

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
