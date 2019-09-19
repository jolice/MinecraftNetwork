package io.riguron.bukkit.item.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

/**
 * JPA entity wrapping persistent ItemStack with the position
 */
@Data
@Setter(AccessLevel.NONE)
@Entity
public class InventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private final ItemStackEntity item;

    @Column
    @NonNull
    private final int position;

}
