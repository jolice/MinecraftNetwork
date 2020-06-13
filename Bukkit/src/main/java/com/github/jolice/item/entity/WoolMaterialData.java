package com.github.jolice.item.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.DyeColor;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class WoolMaterialData extends MaterialDataEntity {

    @Enumerated(value = EnumType.STRING)
    private DyeColor dyeColor;

    @Override
    MaterialData toMaterialData() {
        return new Wool(dyeColor);
    }
}
