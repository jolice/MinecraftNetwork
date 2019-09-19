package io.riguron.system.punishment.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import io.riguron.system.player.PlayerProfile;
import io.riguron.system.punishment.model.type.PunishmentType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Inheritance
@Entity
@Data
@Setter(AccessLevel.NONE)
public class PunishmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private LocalDateTime date = LocalDateTime.now();

    /**
     * Who punished the player.
     */
    @Column
    private String punisher;

    /**
     * Punished player.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PlayerProfile target;

    @Column
    private String reason;

    @Column
    @Enumerated(EnumType.STRING)
    private PunishmentType punishmentType;

    public PunishmentRecord(String punisher, PlayerProfile target, String reason, PunishmentType punishmentType) {
        this.punisher = punisher;
        this.target = target;
        this.reason = reason;
        this.punishmentType = punishmentType;
    }

    @Override
    public String toString() {
        return String.join(", ", Arrays.asList(
                "Type: " + punishmentType.getDescription(),
                "Date: " + date,
                "Reason: " + reason,
                "By: " + punisher
        ));
    }


}
