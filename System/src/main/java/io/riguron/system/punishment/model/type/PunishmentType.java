package io.riguron.system.punishment.model.type;

import lombok.Getter;

public enum PunishmentType {

    BAN("banned"), KICK("kicked"), MUTE("muted"), WARN("warned");

    @Getter
    private String description;

    PunishmentType(String description) {
        this.description = description;
    }
}
