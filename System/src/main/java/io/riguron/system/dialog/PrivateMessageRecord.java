package io.riguron.system.dialog;

import io.ebean.annotation.CreatedTimestamp;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import io.riguron.system.player.PlayerProfile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Setter(AccessLevel.NONE)
public class PrivateMessageRecord {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_id")
    private final PlayerProfile from;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_id")
    private final PlayerProfile to;

    @Column(length = 250)
    private final String text;

    @CreatedTimestamp
    private LocalDateTime date;

}
