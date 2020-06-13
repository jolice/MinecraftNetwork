package com.github.jolice.item.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bukkit.material.MaterialData;

import javax.persistence.*;

/**
 * JPA wrapper for MaterialData.
 */
@Entity
@Inheritance
@Data
@Setter(AccessLevel.NONE)
public abstract class MaterialDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    abstract MaterialData toMaterialData();
}
