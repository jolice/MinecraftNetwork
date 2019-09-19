package io.riguron.system.player;

import io.ebean.annotation.CreatedTimestamp;
import io.riguron.system.preferences.PlayerPreferences;
import io.riguron.system.shop.PlayerPurchases;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import io.riguron.system.internalization.Localizable;
import io.riguron.system.player.exception.InsufficientFundsException;
import io.riguron.system.preferences.PlayerPreferences;
import io.riguron.system.rank.Rank;
import io.riguron.system.shop.PlayerPurchases;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "players", indexes = @Index(columnList = "uuid"))
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlayerProfile implements Localizable {

    /**
     * Auto-generated ID. This field is a service one and not involved
     * anywhere except the database schema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rank rank;

    @Column(nullable = false, unique = true, updatable = false)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private UUID uuid;

    public PlayerProfile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.playerPreferences = new PlayerPreferences(this);
        this.registrationDate = LocalDateTime.now();
        this.purchases = new PlayerPurchases(uuid);
    }

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    @Setter(AccessLevel.NONE)
    private long coins;

    @CreatedTimestamp
    private LocalDateTime registrationDate;

    @Column
    private LocalDateTime lastLogin;

    /**
     * Total time the player has spent playing on the server.
     */
    @Column
    private long playTime;

    /**
     * Player's language.
     */
    @Column
    private Locale locale = Locale.ENGLISH;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true, mappedBy = "profile")
    @Setter(AccessLevel.NONE)
    private PlayerPreferences playerPreferences;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private PlayerPurchases purchases;

    /**
     * Service field for optimistic locking.
     */
    @Version
    private int version;

    public boolean hasPermission(String permission) {
        return rank.hasPermission(permission);
    }

    public void withdraw(long sum) {
        if (sum > coins) {
            throw new InsufficientFundsException();
        }
        this.coins -= sum;
    }

    public void deposit(long sum) {
        this.coins += sum;
    }
}
