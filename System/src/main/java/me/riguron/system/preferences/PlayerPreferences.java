package me.riguron.system.preferences;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.riguron.system.player.PlayerProfile;

import javax.persistence.*;

@Entity
@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlayerPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private final PlayerProfile profile;

    /**
     * Whether the player prefers to accept private messages
     */
    @Column
    private boolean privateMessages = true;

    /**
     * Whether the player sees the chat messages sent by other players
     */
    @Column
    private boolean showChat = true;

    /**
     * Whether the player prefers to accept party requests
     */
    @Column
    private boolean partyRequests = true;


}
