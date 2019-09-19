package io.riguron.system.dialog.ignore;

import io.ebean.annotation.CreatedTimestamp;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import io.riguron.system.player.PlayerProfile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Record that holds the data about the fact that some player A ignores
 * some other player B (i.e player B is not able to send private messages
 * to the player A).
 */
@Entity
@UniqueConstraint(columnNames = {"user_id", "target_id"})
@Data
@Setter(AccessLevel.NONE)
public class IgnoreData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * ID of the player that ignores other player.
     */
    @Column
    private UUID userId;

    /**
     * A player being ignored.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_id")
    private PlayerProfile target;

    /**
     * Date of record creation.
     */
    @CreatedTimestamp
    private LocalDateTime date;

    public IgnoreData(UUID userId, PlayerProfile target) {
        this.userId = userId;
        this.target = target;
    }
}
