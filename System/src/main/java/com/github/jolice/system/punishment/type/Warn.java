package com.github.jolice.system.punishment.type;

import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.punishment.model.type.PunishmentType;
import com.github.jolice.system.punishment.param.PunishmentParameters;
import com.github.jolice.system.punishment.repository.PunishmentDataProvider;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.punishment.service.PunishmentService;
import com.github.jolice.system.punishment.type.active.Ban;
import lombok.RequiredArgsConstructor;

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