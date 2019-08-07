package me.riguron.system.punishment.type;

import lombok.RequiredArgsConstructor;
import me.riguron.system.punishment.model.PunishmentData;
import me.riguron.system.punishment.model.type.PunishmentType;
import me.riguron.system.punishment.param.PunishmentParameters;
import me.riguron.system.punishment.repository.PunishmentDataProvider;
import me.riguron.system.punishment.repository.PunishmentDataRepository;
import me.riguron.system.punishment.service.PunishmentService;
import me.riguron.system.punishment.type.active.Ban;

@RequiredArgsConstructor
public class Warn implements Punishment {

    private final PunishmentService punishmentService;
    private final PunishmentDataProvider dataProvider;
    private final PunishmentDataRepository punishmentDataRepository;
    private final Ban ban;
    private final int warnThreshold;

    @Override
    public PunishResult punish(PunishmentParameters punishmentParameters) {
        return punishmentService.punish(punishmentParameters, PunishmentType.WARN).ifPresent(punishmentRecord -> {
            PunishmentData punishmentData = dataProvider.getPunishmentsFor(punishmentRecord.getTarget().getUuid());
            if (punishmentData.getWarns() == warnThreshold) {
                ban.punish(punishmentParameters);
                punishmentData.setWarns(0);
            } else {
                punishmentData.setWarns(punishmentData.getWarns() + 1);
            }
            punishmentDataRepository.update(punishmentData);
        });

    }


}