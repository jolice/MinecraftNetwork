package me.riguron.game.kit;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import me.riguron.bukkit.item.entity.InventoryItemEntity;
import me.riguron.game.kit.permissions.KitPermissions;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SequenceGenerator(name = "Purchasable", sequenceName = "purchasable_sequence")
@Setter(AccessLevel.NONE)
public class Kit {

    /**
     * Internal ID of a purchasable item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Purchasable")
    private int id;

    /**
     * Items given to the player when he obtains the kit.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
    private List<InventoryItemEntity> items;

    @Embedded
    private KitInformation kitInformation;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    private KitPermissions kitPermissions;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> description;

    public Kit(KitInformation kitInformation, KitPermissions kitPermissions, List<InventoryItemEntity> items, List<String> description) {
        this.kitInformation = kitInformation;
        this.kitPermissions = kitPermissions;
        this.description = description;
        this.items = items;
    }

    public boolean isPurchasable() {
        return getKitInformation().getPrice() > 0;
    }

}
