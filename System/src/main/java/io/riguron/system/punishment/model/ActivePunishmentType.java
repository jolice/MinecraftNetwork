package io.riguron.system.punishment.model;

import lombok.Getter;
import io.riguron.system.punishment.model.type.PunishmentType;

public enum ActivePunishmentType {

    BAN(PunishmentType.BAN), MUTE(PunishmentType.MUTE);

    @Getter
    private PunishmentType mainType;

    ActivePunishmentType(PunishmentType mainType) {
        this.mainType = mainType;
    }
}
