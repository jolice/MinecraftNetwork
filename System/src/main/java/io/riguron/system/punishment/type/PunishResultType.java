package io.riguron.system.punishment.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PunishResultType {

    OK("Punishment applied"), NOT_FOUND("Profile not found");

    private final String description;

}
