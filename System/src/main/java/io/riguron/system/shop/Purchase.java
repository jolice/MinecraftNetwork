package io.riguron.system.shop;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import io.riguron.system.player.PlayerProfile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Setter(AccessLevel.NONE)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * I.e who made a purchase
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private PlayerProfile owner;

    /**
     * ID of purchased item, must be unique throughout the system
     */
    @Column(nullable = false)
    private int purchaseId;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private String purchaseDescription;

    @Column
    private LocalDateTime dateTime = LocalDateTime.now();

    public Purchase(long price, int purchaseId, String purchaseDescription, PlayerProfile owner) {
        this.price = price;
        this.purchaseDescription = purchaseDescription;
        this.owner = owner;
        this.purchaseId = purchaseId;
    }
}
