package com.github.jolice.system.punishment.model;

import lombok.EqualsAndHashCode;
import com.github.jolice.system.player.PlayerProfile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Active punishment is a type of punishment that is applied to a player
 * for a certain period of time (or forever). This type of punishment includes
 * ban and mute.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
public class ActivePunishmentRecord extends PunishmentRecord {

    @Column
    private LocalDateTime expiration;

    @Enumerated(value = EnumType.STRING)
    private ActivePunishmentType activeType;

    public ActivePunishmentRecord(String punisher, PlayerProfile target, String reason, LocalDateTime expiration, ActivePunishmentType activeType) {
        super(punisher, target, reason, activeType.getMainType());
        this.expiration = expiration;
        this.activeType = activeType;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        if (isTemporary()) {
            result.append(", ");
            result.append("Expires in: ");
            result.append(expiresIn());
        }
        return result.toString();
    }

    public boolean isExpired() {
        return LocalDateTime.now().compareTo(expiration) > 0;
    }

    private Duration expiresIn() {
        return Duration.between(LocalDateTime.now(), expiration);
    }

    private boolean isTemporary() {
        return !LocalDateTime.MIN.equals(expiration);
    }
}
