package com.github.jolice.item.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import com.github.jolice.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.util.List;

/**
 * JPA wrapper for native item stack.
 */
@Entity
@Data
@Setter(AccessLevel.NONE)
public class ItemStackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Material material;

    @OneToOne(cascade = CascadeType.ALL)
    @Setter
    private MaterialDataEntity materialData;

    @Column
    private String displayName;

    /**
     * Eager fetching is enabled intentionally for convenience.
     * ItemStack is always fetched with lore.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> lore;

    public ItemStackEntity(Material material, String displayName, List<String> lore) {
        this.material = material;
        this.displayName = displayName;
        this.lore = lore;
    }

    public ItemStack toItemStack() {
        return new ItemStackBuilder(material)
                .materialData(this.materialData.toMaterialData())
                .displayName(displayName)
                .lore(this.lore)
                .build();
    }
}
