package io.riguron.system.punishment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * This entity is responsible for holding active punishments applied to the
 * player, as well as his warns.
 */
@Entity
@Getter
@RequiredArgsConstructor
public class PunishmentData {

    @Id
    private final UUID id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
    @MapKey(name = "activeType")
    private Map<ActivePunishmentType, ActivePunishmentRecord> activePunishments = new EnumMap<>(ActivePunishmentType.class);

    @Setter
    private int warns;

    public Optional<ActivePunishmentRecord> getPunishment(ActivePunishmentType punishmentType) {
        return Optional.ofNullable(this.activePunishments.get(punishmentType));
    }

    public void addPunishment(ActivePunishmentType punishmentType, ActivePunishmentRecord activePunishmentRecord) {
        this.activePunishments.put(punishmentType, activePunishmentRecord);
    }

    public void removePunishment(ActivePunishmentType punishmentType) {
        this.activePunishments.remove(punishmentType);
    }


}
