package io.riguron.system.punishment.type;

import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import io.riguron.system.punishment.service.PunishmentService;
import io.riguron.system.punishment.type.active.Ban;
import lombok.RequiredArgsConstructor;
import io.riguron.system.punishment.model.PunishmentData;
import io.riguron.system.punishment.model.type.PunishmentType;
import io.riguron.system.punishment.param.PunishmentParameters;
import io.riguron.system.punishment.repository.PunishmentDataProvider;
import io.riguron.system.punishment.repository.PunishmentDataRepository;
import io.riguron.system.punishment.service.PunishmentService;
import io.riguron.system.punishment.type.active.Ban;

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