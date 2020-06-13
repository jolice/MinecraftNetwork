package com.github.jolice.system.punishment.model;

import com.github.jolice.system.punishment.model.type.PunishmentType;
import lombok.Getter;

public enum ActivePunishmentType {

    BAN(PunishmentType.BAN), MUTE(PunishmentType.MUTE);

    @Getter
    private PunishmentType mainType;

    ActivePunishmentType(PunishmentType mainType) {
        this.mainType = mainType;
    }
}
